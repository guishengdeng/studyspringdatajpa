package com.biz.message.amqp;

/**
 * 用于封装amqp消息路由数据的pojo
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 */
public class AmqpRouter {
    private String routingKey;
    private String exchangeName;

    public AmqpRouter(String exchangeName, String routingKey) {
        super();
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

}
