package com.auth.config;

import com.auth.service.AuthService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import saturn.common.service.JsonService;
import saturn.common.service.RabbitService;

@Configuration
@ComponentScan(basePackageClasses = {RabbitService.class, AuthService.class, JsonService.class})
public class AppConfig {

}
