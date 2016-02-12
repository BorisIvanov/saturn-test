package saturn.auth.config;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import saturn.auth.domain.Account;
import saturn.auth.repository.AccountRepository;

import java.util.UUID;

@Component
public class DbInit implements InitializingBean {

    private AccountRepository accountRepository;
    private int tokenExpireDay;
    public static final String DEFAULT_USER_NAME = "TEST_USER";
    public static final String DEFAULT_USER_PASSWORD = "TEST_USER";

    @Autowired
    public DbInit(AccountRepository accountRepository, Environment env){
        this.accountRepository = accountRepository;
        this.tokenExpireDay = Integer.valueOf(env.getProperty("app.token.expire"));
    }

    @Override
    public void afterPropertiesSet() {
        Account account = new Account();
        account.setName(DEFAULT_USER_NAME);
        account.setPassword(DEFAULT_USER_PASSWORD);
        account.setToken(UUID.randomUUID());
        account.setTokenExpire(DateTime.now(DateTimeZone.UTC).plusDays(tokenExpireDay));
        accountRepository.add(account);
    }

}
