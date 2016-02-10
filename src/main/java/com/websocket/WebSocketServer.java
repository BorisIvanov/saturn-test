package com.websocket;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.core.Context;

import com.websocket.session.RedisWebSocketSessionRepository;
import com.websocket.session.WebSocketSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/auth")
public class WebSocketServer {

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    @OnMessage
    public void handleMessage(Session session, String message) {
        session.getAsyncRemote().sendText("Hello");
    }

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
    }

}
