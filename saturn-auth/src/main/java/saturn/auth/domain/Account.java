package saturn.auth.domain;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "account" )
public class Account {
    private Long id;
    private String name;
    private String password;
    private UUID token;
    private DateTime tokenExpire;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "token")
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @Column(name = "token_expire")
    public DateTime getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(DateTime tokenExpire) {
        this.tokenExpire = tokenExpire;
    }
}
