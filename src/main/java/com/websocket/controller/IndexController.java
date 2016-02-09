package com.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    private SimpMessagingTemplate template;

    @Autowired
    public IndexController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @MessageMapping("/auth")
    @SendTo("/user")
    public Object addNum(Object input) throws Exception {
        Thread.sleep(2000);
        //Result result = new Result(input.getNum1()+"+"+input.getNum2()+"="+(input.getNum1()+input.getNum2()));
        return new Object();
    }

}
