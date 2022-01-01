package org.example.controller;

import org.example.constant.DirectConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduceController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendMsg")
    public String sendMessage(@RequestParam("msg") String msg) {
        rabbitTemplate.convertAndSend(DirectConstant.DIRECT_EXCHANGE_NAME,
                DirectConstant.DIRECT_ROUTING_KEY, msg);
        return msg + ": send success";
    }
}
