package com.auth.service;

import com.auth.domain.Account;
import com.auth.domain.Token;
import com.auth.repository.AccountRepository;
import com.auth.repository.TokenRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import saturn.common.protocol.AuthRequest;
import saturn.common.protocol.AuthResponse;
import saturn.common.protocol.AuthResponseData;
import saturn.common.protocol.CustomerError;
import saturn.common.service.JsonService;
import saturn.common.service.QueueConsumer;
import saturn.common.service.RabbitService;

import java.util.UUID;

@Service
public class AuthService implements QueueConsumer {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private AccountRepository accountRepository;
    private TokenRepository tokenRepository;
    private JsonService jsonService;
    private RabbitService rabbitService;
    private int tokenExpireDay;

    @Autowired
    public AuthService(AccountRepository accountRepository,
                       TokenRepository tokenRepository,
                       JsonService jsonService,
                       Environment env){
        this.accountRepository = accountRepository;
        this.tokenRepository = tokenRepository;
        this.jsonService = jsonService;
        this.tokenExpireDay = Integer.valueOf(env.getProperty("app.token.expire"));
    }

    @Override
    public void setRabbitService(RabbitService rabbitService){
        this.rabbitService = rabbitService;
    }

    public AuthResponseData auth(String name, String password) {
        Account account = accountRepository.find(name, password);

        Token token = new Token();
        token.setAccountId(account.getId());
        token.setDate(DateTime.now(DateTimeZone.UTC));
        token.setValue(account.getToken());
        token.setTokenExpire(account.getTokenExpire());
        tokenRepository.add(token);

        account.setToken(UUID.randomUUID());
        account.setTokenExpire(DateTime.now(DateTimeZone.UTC).plusDays(this.tokenExpireDay));
        accountRepository.update(account);

        AuthResponseData result = new AuthResponseData();
        result.setApiToken(account.getToken());
        result.setApiTokenExpirationDate(account.getTokenExpire());
        return result;
    }

    @Override
    public void onMessage(Message message) {
        UUID sequenceId;
        AuthRequest authRequest;
        String sessionId;

        try {
            authRequest = jsonService.readValue(message.getBody(), AuthRequest.class);
            sequenceId = authRequest.getSequenceId();
            sessionId = authRequest.getSessionId();
        } catch (Exception e){
            logger.error("onMessage", e);
            return;
        }

        try{
            AuthResponse result = new AuthResponse();
            result.setSessionId(sessionId);
            result.setSequenceId(sequenceId);
            result.setData(auth(authRequest.getData().getEmail(), authRequest.getData().getPassword()));
            rabbitService.convertAndSend(result);
        } catch(Exception e){
            logger.error("onMessage", e);
            CustomerError customerError = new CustomerError();
            customerError.setSequenceId(sequenceId);
            customerError.setSessionId(sessionId);
            rabbitService.convertAndSend(customerError);
        }
    }
}
