package com.auth.domain;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "token" )
public class Token {
    private Long id;
    private Long accountId;
    private DateTime date;
    private UUID value;
    private DateTime tokenExpire;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "token_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "account_id")
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Column(name = "token_date")
    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    @Column(name = "token_value")
    public UUID getValue() {
        return value;
    }

    public void setValue(UUID value) {
        this.value = value;
    }

    @Column(name = "token_expire")
    public DateTime getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(DateTime tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

}
