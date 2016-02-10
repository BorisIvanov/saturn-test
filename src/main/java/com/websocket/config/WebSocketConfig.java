package com.websocket.config;

import com.websocket.handlers.WsHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.session.ExpiringSession;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSessionWebSocketMessageBrokerConfigurer<ExpiringSession> {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/user");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/auth")/*.withSockJS()*//*
                .setInterceptors(
                        new WsHandshakeInterceptor(),
                        new HttpSessionHandshakeInterceptor())*/;
    }

    //https://github.com/ualerts-org/ualerts-server/blob/master/org.ualerts.fixed/org.ualerts.fixed.web/src/main/java/org/ualerts/fixed/web/config/StompConfig.java
/*
    private final SimpleUserQueueSuffixResolver userQueueSuffixResolver =
            new SimpleUserQueueSuffixResolver();
    *//*
    @Bean
    public StompWebSocketHandler stompWebSocketHandler() {
        StompWebSocketHandler handler =
                new StompWebSocketHandler(dispatchChannel());
        handler.setUserQueueSuffixResolver(this.userQueueSuffixResolver);
        webSocketHandlerChannel().subscribe(handler);
        return handler;
    }*/

}