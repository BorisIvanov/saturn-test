package com.websocket.controller;

import com.auth.protocol.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

@Controller
public class IndexController {
    private SimpMessagingTemplate template;
    @Autowired
    ServletContext servletContext;

    @Autowired
    public IndexController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @MessageMapping("/auth")
    //@SendTo("/user")
    @SendToUser(value = "/user", broadcast = false)
    public Object auth(AuthRequest authRequest, @Headers Map headers, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        //template.
        return authRequest;
    }

}
