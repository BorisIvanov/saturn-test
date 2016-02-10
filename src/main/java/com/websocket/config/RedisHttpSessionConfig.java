package com.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


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

}
