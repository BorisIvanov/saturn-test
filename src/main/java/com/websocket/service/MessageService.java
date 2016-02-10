package com.websocket.service;

import com.websocket.WebSocketServer;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;
import saturn.common.service.QueueConsumer;
import saturn.common.service.RabbitService;

import javax.websocket.Session;

@Service
public class MessageService implements QueueConsumer {

    private RabbitService rabbitService;

    @Override
    public void onMessage(Message message) {
        WebSocketServer.send("", "");
    }

    public void send(Session session, String message){
        //rabbitService
    }

}