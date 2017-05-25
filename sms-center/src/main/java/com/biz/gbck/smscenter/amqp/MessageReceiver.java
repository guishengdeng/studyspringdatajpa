package com.biz.gbck.smscenter.amqp;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.smscenter.interfaces.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Function: receive message from mq server
 * Created by david-liu on 3/13/16.
 */
public class MessageReceiver implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    private static final Long THIRTY_SECONDS = 30 * 1000L;

    private Notification notifyPublisher;

    private ConcurrentLinkedQueue<com.biz.gbck.smscenter.common.Message> messageStack = new ConcurrentLinkedQueue<>();

    private Long locateTime = System.currentTimeMillis();

    @Override
    public void onMessage(Message message) {
        Long currentTime = System.currentTimeMillis();
        logger.info("locateTime=[{}], currentTime=[{}]", locateTime, currentTime);
        if (currentTime - locateTime > THIRTY_SECONDS) {
            logger.info("间隔已超过30s清理储存消息, 清除消息数量[{}]", messageStack.size());
            messageStack.clear();
            locateTime = currentTime;
        }
        logger.info("从消息队列里面收取到消息[{}]", new String(message.getBody()));
        try {
            com.biz.gbck.smscenter.common.Message msg = JSON.parseObject(new String(message.getBody()),
                    com.biz.gbck.smscenter.common.Message.class);
            if (!isStacked(msg)) {
                messageStack.add(msg);
                notifyPublisher.sendNotice(msg);
            } else {
                logger.info("检测到消息[{}]已经被缓存, 不发送消息");
            }
        } catch (Exception e) {
            logger.warn("消息反序列化失败");
        }

    }

    public void setNotifyPublisher(Notification notifyPublisher) {
        this.notifyPublisher = notifyPublisher;
    }

    private boolean isStacked(com.biz.gbck.smscenter.common.Message message) {
        return messageStack.contains(message);
    }
}
