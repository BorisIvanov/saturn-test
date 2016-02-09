package com.auth.config;

import com.auth.service.JsonService;
import com.auth.service.RabbitService;
import com.auth.service.AuthService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {RabbitService.class, AuthService.class, JsonService.class})
public class AppConfig {

}
