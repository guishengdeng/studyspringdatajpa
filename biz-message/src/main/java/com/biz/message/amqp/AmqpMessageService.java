package com.biz.message.amqp;


import com.biz.message.AbstractBaseMessageService;
import com.biz.message.BizMessage;
import com.biz.message.QueueDefinition;
import com.biz.message.QueueParser;
import com.biz.message.mo.AmqpSendStatus;
import org.codelogger.utils.ValueUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;

//import com.bozhi.message.MessageListener;

public class AmqpMessageService extends AbstractBaseMessageService {


    private AmqpTemplate amqpTemplate;

    private Integer maxRetry;

    private Long retryElapse;

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void setMaxRetry(Integer maxRetry) {
        this.maxRetry = maxRetry;
    }

    public void setRetryElapse(Long retryElapse) {
        this.retryElapse = retryElapse;
    }

    @Override
    public AmqpSendStatus send(QueueDefinition queue, BizMessage<?> message) {
        return send(getRouter(queue), message.getPayload(), this.maxRetry, this.retryElapse);
    }

    @Override
    protected <T> T receive(QueueDefinition queueDefinition, Class<T> msgType) {
        Message msg = amqpTemplate.receive(new QueueParser(queueDefinition).getName());
        return messageConverter.fromMessageString(new String(msg.getBody()), msgType);
    }

    private AmqpRouter getRouter(QueueDefinition queue) {
        QueueParser parser = new QueueParser(queue);
        logger.info("queue --parser--> {}", parser);
        return new AmqpRouter(parser.getExchangeName(), parser.getRoutingKey());
    }


    private AmqpSendStatus send(AmqpRouter router, Object message, Integer maxRetry, Long retryElapse) {
        String exchangeName = router.getExchangeName();
        String routingKey = router.getRoutingKey();
        logger.debug("send message to queue using amqp, exchange={}, routing key={}, message={}", exchangeName, routingKey, message);
        try {
            amqpTemplate.convertAndSend(exchangeName, routingKey, message);
            return new AmqpSendStatus(Boolean.TRUE, null, null);
        } catch (org.springframework.amqp.AmqpConnectException e) {
            if (ValueUtils.getValue(maxRetry) == 0) {
                logger.error("发送消息失败", e);
                return new AmqpSendStatus(Boolean.FALSE, e.getMessage(), e.getCause());
            } else {
                logger.warn("发送消息失败, 重试创建连接次数[{}], 重试间隔[{}]ms", ValueUtils.getValue(maxRetry), ValueUtils.getValue(retryElapse));
                if (ValueUtils.getValue(retryElapse) > 0) {
                    try {
                        Thread.sleep(retryElapse);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
                return this.send(router, message, --maxRetry, retryElapse);
            }
        }
    }
}
