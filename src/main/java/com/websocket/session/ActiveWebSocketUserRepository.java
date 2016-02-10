package com.websocket.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ActiveWebSocketUserRepository {

    Map<String, ActiveWebSocketUser> map = new HashMap<>();

    public ActiveWebSocketUser findOne(String id) {
        return map.get(id);
    }

    public void delete(String id) {
        map.remove(id);
    }

    public void save(ActiveWebSocketUser user) {
        map.put(user.getId(), user);
    }
}