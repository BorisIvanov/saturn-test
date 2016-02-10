package com.websocket.handlers;

import com.websocket.session.ActiveWebSocketUser;
import com.websocket.session.ActiveWebSocketUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Arrays;

@Component
public class WebSocketDisconnectHandler<S> implements ApplicationListener<SessionDisconnectEvent> {
    @Autowired
    private ActiveWebSocketUserRepository repository;
    //private SimpMessageSendingOperations messagingTemplate;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    public void onApplicationEvent(SessionDisconnectEvent event) {
        String id = event.getSessionId();
        if(id == null) {
            return;
        }
        ActiveWebSocketUser user = repository.findOne(id);
        if(user == null) {
            return;
        }

        repository.delete(id);
        messagingTemplate.convertAndSend("/topic/friends/signout", Arrays.asList(user.getUsername()));
    }
}