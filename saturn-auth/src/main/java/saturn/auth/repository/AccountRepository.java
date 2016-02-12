package saturn.auth.repository;

import saturn.auth.domain.Account;

public interface AccountRepository {

    void add(Account account);

    void update(Account account);

    Account find(String name, String password);

}
