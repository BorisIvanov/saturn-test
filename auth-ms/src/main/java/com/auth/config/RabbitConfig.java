package com.auth.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import saturn.common.service.RabbitService;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@PropertySource("classpath:application.properties")
public class RabbitConfig {

    @Autowired
    private Environment env;

    @Bean
    public ConnectionFactory rabbitConnectionFactory() throws URISyntaxException {
        return new CachingConnectionFactory(new URI(env.getProperty("rabbit.url")));
    }

    @Bean
    public AmqpAdmin amqpAdmin() throws URISyntaxException {
        return new RabbitAdmin(rabbitConnectionFactory());
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
    public SimpleMessageListenerContainer messageListenerContainer() throws URISyntaxException {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rabbitConnectionFactory());
        container.setQueueNames(env.getProperty("rabbit.queue.receive"));
        container.setMessageListener(rabbitService());
        return container;
    }

    @Bean
    public RabbitService rabbitService() throws URISyntaxException {
        return new RabbitService(rabbitConnectionFactory(), env.getProperty("rabbit.queue.send"));
    }


}
