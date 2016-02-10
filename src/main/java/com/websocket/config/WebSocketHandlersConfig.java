package com.websocket.config;

import com.websocket.handlers.WebSocketConnectHandler;
import com.websocket.handlers.WebSocketDisconnectHandler;
import com.websocket.session.ActiveWebSocketUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.session.ExpiringSession;

@Configuration
@ComponentScan({ "com.websocket.handlers" })
public class WebSocketHandlersConfig<S extends ExpiringSession> {

    @Bean
    public WebSocketConnectHandler<S> webSocketConnectHandler() {
        return new WebSocketConnectHandler<S>();
    }

    @Bean
    public WebSocketDisconnectHandler<S> webSocketDisconnectHandler() {
        return new WebSocketDisconnectHandler<S>();
    }
}
