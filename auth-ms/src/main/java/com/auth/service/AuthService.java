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

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private RabbitService rabbitService;


    public AuthResponseData auth(String name, String password) {
        Account account = accountRepository.find(name, password);

        Token token = new Token();
        token.setAccountId(account.getId());
        token.setDate(DateTime.now(DateTimeZone.UTC));
        token.setValue(account.getToken());
        token.setTokenExpire(account.getTokenExpire());
        tokenRepository.add(token);

        account.setToken(UUID.randomUUID());
        account.setTokenExpire(DateTime.now(DateTimeZone.UTC).plusDays(30));
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

        try {
            authRequest = jsonService.readValue(message.getBody(), AuthRequest.class);
            sequenceId = authRequest.getSequenceId();
        } catch (Exception e){
            logger.error("onMessage", e);
            return;
        }

        try{
            AuthResponse result = new AuthResponse();
            result.setSequenceId(sequenceId);
            result.setData(auth(authRequest.getData().getEmail(), authRequest.getData().getPassword()));
            rabbitService.convertAndSend(result);
        } catch(Exception e){
            logger.error("onMessage", e);
            CustomerError customerError = new CustomerError();
            customerError.setSequenceId(sequenceId);
            rabbitService.convertAndSend(customerError);
        }
    }
}
