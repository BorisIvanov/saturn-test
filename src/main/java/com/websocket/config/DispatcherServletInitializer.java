package com.websocket.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;


public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        super.onStartup(context);

        String activeProfile = System.getProperty("application.property");
        if (activeProfile == null) {
            activeProfile = "prod"; // or whatever you want the default to be
        }
        context.setInitParameter("spring.profiles.active", activeProfile);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //return new Class<?>[]{WebConfig.class, RedisHttpSessionConfig.class, WebSocketHanderConfig.class};
        return new Class<?>[]{WebConfig.class, RedisHttpSessionConfig.class, WebSocketConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setInitParameter("dispatchOptionsRequest", "true");
    }

}
