package saturn.auth.repository;


import saturn.auth.domain.Account;
import saturn.auth.domain.Token;

import java.util.List;

public interface TokenRepository {

    List<Token> list(Account account);

    void add(Token token);

}
