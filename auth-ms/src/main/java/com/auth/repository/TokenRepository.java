package com.auth.repository;


import com.auth.domain.Account;
import com.auth.domain.Token;

import java.util.List;

public interface TokenRepository {

    List<Token> list(Account account);

    void add(Token token);

}
