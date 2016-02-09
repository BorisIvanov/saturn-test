package com.auth.service;

import com.auth.protocol.AuthRequest;
import com.auth.protocol.AuthResponse;
import com.auth.protocol.CustomerError;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RabbitService extends RabbitTemplate {
    private static final Logger logger = LoggerFactory.getLogger(RabbitService.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private JsonService jsonService;

    @Autowired
    public RabbitService(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        //converter.
        converter.setJsonObjectMapper(new JsonService());
        setMessageConverter(converter);
    }

    @Override
    public void onMessage(Message message) {
        UUID sequenceId;
        AuthRequest authRequest;

        try {
            authRequest = jsonService.readValue(message.getBody(), AuthRequest.class);
            sequenceId = authRequest.getSequenceId();
        } catch (Exception e){
            logger.error("onMessage", e);
            return;
        }

        try{
            AuthResponse result = new AuthResponse();
            result.setSequenceId(sequenceId);
            result.setData(authService.auth(authRequest.getData().getEmail(), authRequest.getData().getPassword()));
            convertAndSend("node-0-in", result);
        } catch(Exception e){
            logger.error("onMessage", e);
            CustomerError customerError = new CustomerError();
            customerError.setSequenceId(sequenceId);
            convertAndSend("node-0-in", customerError);
        }
    }

    @Override
    public void convertAndSend(String routingKey, Object message) {/*
        String data = null;
        try {
            data = jsonService.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            logger.error("convertAndSend", e);
        }*/
        super.convertAndSend(routingKey, message, messagePostProcessor -> {
            messagePostProcessor.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
            return messagePostProcessor;
        });
    }

}
