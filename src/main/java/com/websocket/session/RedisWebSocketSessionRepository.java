package com.websocket.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.websocket.Session;

@Repository

public class RedisWebSocketSessionRepository implements WebSocketSessionRepository {

    @Autowired
    private RedisTemplate<String, Session> redisTemplate;

    private static final String OBJECT_KEY = "WEB_SOCKET_SESSION";
    private static final String HASH_KEY = "WEB:SOCKET:SESSION:";

    public void put(Session session) {
        redisTemplate.opsForHash().put(OBJECT_KEY, HASH_KEY + session.getId(), session);
    }

    public void delete(Session session) {
        redisTemplate.opsForHash().delete(OBJECT_KEY, HASH_KEY + session.getId());
    }

    public Session get(String id) {
        return (Session)redisTemplate.opsForHash().get(OBJECT_KEY, HASH_KEY + id);
    }
}
