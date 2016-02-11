package com.auth.repository.impl;

import org.hibernate.Session;

public class BaseRepository<T> {

    public Session openSession(){
        throw new UnsupportedOperationException();
    }

    public void add(T object){
        Session session = openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }
}
