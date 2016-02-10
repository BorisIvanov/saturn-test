package com.websocket.config;

import com.websocket.SpringApplicationContext;
import com.websocket.session.RedisWebSocketSessionRepository;
import com.websocket.session.WebSocketSessionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.websocket.Session;

@Configuration
@EnableRedisHttpSession
public class RedisHttpSessionConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() throws Exception {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("pub-redis-10367.eu-central-1-1.1.ec2.redislabs.com");
        jedisConnectionFactory.setPassword("1234qwer");
        jedisConnectionFactory.setPort(10367);
        jedisConnectionFactory.setUsePool(true);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Session> webSocketSessionRedisTemplate() throws Exception {
        RedisTemplate<String, Session> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(connectionFactory());
        return template;
    }
/*
    @Bean
    public WebSocketSessionRepository webSocketSessionRepository(){
        return new RedisWebSocketSessionRepository();
    }*/



}
