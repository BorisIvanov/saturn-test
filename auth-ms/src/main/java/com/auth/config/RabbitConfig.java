package com.auth.config;

import com.auth.service.RabbitService;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() throws URISyntaxException {
        return new CachingConnectionFactory(new URI("amqp://cixyzlsr:LyTGv2DDxxzmDt6bDNHbo7hFatBlTEPa@spotted-monkey.rmq.cloudamqp.com/cixyzlsr"));
    }

    @Bean
    public AmqpAdmin amqpAdmin() throws URISyntaxException {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Queue queueNode0In() {
        return new Queue("node-0-in");
    }

    @Bean
    public Queue queueNode0Out() {
        return new Queue("node-0-out");
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer1() throws URISyntaxException {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("node-0-out");
        container.setMessageListener(rabbitService());
        return container;
    }

    @Bean
    public RabbitService rabbitService() throws URISyntaxException {
        return new RabbitService(connectionFactory());
    }


}
