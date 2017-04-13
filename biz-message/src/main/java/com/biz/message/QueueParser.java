package com.biz.message;


import com.biz.core.asserts.SystemAsserts;
import com.biz.core.exceptions.SystemException;
import com.biz.message.amqp.AmqpExchangeType;
import com.biz.message.amqp.AmqpMessageAdmin;

public class QueueParser {

    private String name;
    private String exchangeName;
    private String routingKey;
    private AmqpExchangeType exchangeType;
    private QueueType componentType;


    public QueueParser(QueueDefinition definition) {
        QueueType type = definition.getType();
        SystemAsserts.notNull(definition.getType(), "队列类型不能为null");
        switch (type) {
            case queue:
                this.setExchangeType(AmqpExchangeType.DIRECT);
                this.setQueueType(QueueType.queue);
                this.setExchangeName(AmqpMessageAdmin.DEFAULT_DIRECT_EXCHANGE_NAME);
                break;
            case topic:
                this.setExchangeType(AmqpExchangeType.TOPIC);
                this.setQueueType(QueueType.topic);
                this.setExchangeName(AmqpMessageAdmin.DEFAULT_TOPIC_EXCHANGE_NAME);
                break;
            default:
                throw new SystemException("暂不支持的定义类型:" + type);
        }
        String signature = definition.getSignature();
        SystemAsserts.hasText(signature, "消息组件签名必须有值");
        String[] strs = signature.split(":");
        switch (strs.length) {
            case 1:
                this.setName(strs[0]);
                this.setRoutingKey(strs[0]);
                break;
            case 2:
                this.setName(strs[1]);
                this.setRoutingKey(strs[1]);
                this.setExchangeName(strs[0]);
                break;
            case 3:
                this.setName(strs[2]);
                this.setRoutingKey(strs[1]);
                this.setExchangeName(strs[0]);
                break;
            case 0:
            default:
                throw new SystemException("消息组件定义错误,规则[exchangeName:[routingKey:]]queueName");
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public AmqpExchangeType getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(AmqpExchangeType exchangeType) {
        this.exchangeType = exchangeType;
    }

    public QueueType getQueueType() {
        return componentType;
    }

    public void setQueueType(QueueType componentType) {
        this.componentType = componentType;
    }

    @Override
    public String toString() {
        return "ComponentDefinitionParser [name=" + name + ", exchangeName=" + exchangeName + ", routingKey=" + routingKey
                + ", exchangeType=" + exchangeType + ", componentType=" + componentType + "]";
    }

}
