package com.websocket.handlers;

import java.security.Principal;
import java.util.Arrays;
import java.util.Calendar;

import com.websocket.session.ActiveWebSocketUser;
import com.websocket.session.ActiveWebSocketUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Component
public class WebSocketConnectHandler<S> implements ApplicationListener<SessionConnectEvent> {
    @Autowired
    private ActiveWebSocketUserRepository repository;
   // private SimpMessageSendingOperations messagingTemplate;
    private SimpMessagingTemplate messagingTemplate;


    public void onApplicationEvent(SessionConnectEvent event) {
        MessageHeaders headers = event.getMessage().getHeaders();

        Principal user = SimpMessageHeaderAccessor.getUser(headers);
        if(user == null) {
            return;
        }
        String id = SimpMessageHeaderAccessor.getSessionId(headers);
        repository.save(new ActiveWebSocketUser(id, user.getName(), Calendar.getInstance()));
        messagingTemplate.convertAndSend("/topic/friends/signin", Arrays.asList(user.getName()));
    }
}
