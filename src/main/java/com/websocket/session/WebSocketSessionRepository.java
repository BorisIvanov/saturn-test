package com.websocket.session;

import org.springframework.stereotype.Repository;

import javax.websocket.Session;

@Repository
public interface WebSocketSessionRepository {

    void put(Session session);

    void delete(Session session);

    Session get(String id);

}
