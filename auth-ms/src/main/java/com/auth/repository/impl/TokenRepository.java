package com.auth.repository.impl;


import com.auth.domain.Account;
import com.auth.domain.Token;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TokenRepository implements com.auth.repository.TokenRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Session openSession(){
        return sessionFactory.openSession();
    }


    public List<Token> list(Account account){
        Session session = openSession();
        List<Token> result = (List<Token>) session
                .createCriteria(Token.class)
                .add(Restrictions.eq("account_id", account.getId()))
                .addOrder(Order.desc("token_date"))
                .list();
        session.close();
        return result;
    }

    @Override
    public void add(Token token) {

    }

}
