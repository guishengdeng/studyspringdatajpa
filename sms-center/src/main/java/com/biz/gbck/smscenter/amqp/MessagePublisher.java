package com.biz.gbck.smscenter.amqp;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.smscenter.common.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Function: publish message to mq server
 * Created by david-liu on 3/13/16.
 */
@Service
public class MessagePublisher {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Value("${amqp.routingkey.sms}")
    private String routingKey;

    public void sendMessage(Message message) {
        amqpTemplate.convertAndSend(routingKey, JSON.toJSON(message).toString().getBytes());
    }
}
