package com.loman.controller;

import com.loman.service.ConsumerService;
import com.loman.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2017/4/13.
 */
@Controller
public class MessageController {
    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Resource(name = "demoQueueDestination")
    private Destination destination;

    @Resource(name = "producerService")
    private ProducerService producerService;

    @Resource(name = "consumerService")
    private ConsumerService consumerService;

    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    @ResponseBody
    public void send(@RequestParam("msg")String msg){
        logger.info(Thread.currentThread().getName()+"------send to jms Start");
        producerService.sendMessage(msg);
        logger.info(Thread.currentThread().getName()+"------send to jms end");
    }


    @RequestMapping(value = "/receiveMessage",method = RequestMethod.POST)
    @ResponseBody
    public Object receive(){
        logger.info(Thread.currentThread().getName()+"-----receive from jms Start");
        TextMessage tm = consumerService.receive(destination);
        logger.info(Thread.currentThread().getName()+"-----receive from jms end");
        return tm;
    }
}
