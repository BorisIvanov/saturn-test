package com.websocket.config;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;


public class GreetingHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        try {
            Thread.sleep(3000); // simulated delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TextMessage msg = new TextMessage("Hello, " + message.getPayload() + "!");
        session.sendMessage(msg);
    }
}