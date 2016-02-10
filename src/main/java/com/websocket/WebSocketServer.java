package com.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/auth", configurator = SpringConfigurator.class)
public class WebSocketServer {
    private static Set<Session> clients =
            Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void handleMessage(Session session, String message) {
        System.out.println(session);
        System.out.println(message);
    }

    @OnOpen
    public void onOpen (Session session) {
        // Add session to the connected sessions set
        clients.add(session);
    }

    @OnClose
    public void onClose (Session session) {
        // Remove session from the connected sessions set
        clients.remove(session);
    }

}
