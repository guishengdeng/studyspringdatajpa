package com.biz.soa.promotion.amqp.queue;

import com.biz.message.QueueDefinition;
import com.biz.message.QueueType;

/**
 * Created by david-liu on 2017/05/19 11:14.
 */
public class PromotionQueueDefinition implements QueueDefinition {

    private String exchangeName;

    private String queueName;

    private String queuePrefix;

    public PromotionQueueDefinition(String exchangeName, String queueName, String queuePrefix) {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.queuePrefix = queuePrefix;
    }

    @Override
    public String getSignature() {
        return String.format("%s:%s:%s", exchangeName, String.format("%s.*", queuePrefix), queueName);
    }

    @Override
    public QueueType getType() {
        return QueueType.topic;
    }
}
