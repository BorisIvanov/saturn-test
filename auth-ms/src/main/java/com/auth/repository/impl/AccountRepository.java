package com.auth.repository.impl;


import com.auth.domain.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository implements com.auth.repository.AccountRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(Account account){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Account account){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(account);
        session.getTransaction().commit();
        session.close();
    }

    public Account find(String name, String password){
        Session session = sessionFactory.openSession();
        Account result = (Account) session
                .createCriteria(Account.class)
                .add(Restrictions.eq("name", name))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
        session.close();
        return result;
    }

}
