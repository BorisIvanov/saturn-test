package com.auth.service;

import com.auth.domain.Account;
import com.auth.domain.Token;
import com.auth.protocol.AuthResponseData;
import com.auth.repository.AccountRepository;
import com.auth.repository.TokenRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TokenRepository tokenRepository;

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

}
