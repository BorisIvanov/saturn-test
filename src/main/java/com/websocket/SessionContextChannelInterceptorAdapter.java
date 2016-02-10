package com.websocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.session.Session;

import java.util.Map;

public class SessionContextChannelInterceptorAdapter extends ChannelInterceptorAdapter {
/*
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        Map<String, Object> sessionHeaders = SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders());
        String sessionId = (String) sessionHeaders.get(SESSION_ATTR);
        if (sessionId != null) {
            Session session = sessionRepository.getSession(sessionId);
            if (session != null) {
                sessionRepository.save(session);
            }
        }
        return super.preSend(message, channel);
    }
};*/

}
