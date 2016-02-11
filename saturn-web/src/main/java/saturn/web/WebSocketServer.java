package saturn.web;


import saturn.web.service.MessageService;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/auth")
public class WebSocketServer {

    public WebSocketServer() {
        messageService = SpringApplicationContext.getBean(MessageService.class);
    }

    private static Map<String, Session> clients = Collections.synchronizedMap(new HashMap<>());

    private MessageService messageService;

    public static synchronized void send(String id, String message) {
        if (clients.containsKey(id)) {
            clients.get(id).getAsyncRemote().sendText(message);
        }
    }

    @OnMessage
    public void handleMessage(Session session, String message) {
        messageService.send(session, message);
    }

    @OnOpen
    public void onOpen(Session session) {
        clients.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session.getId());
    }

}