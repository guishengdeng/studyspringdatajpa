package com.biz.message.amqp;


import com.biz.core.exceptions.SystemException;
import com.biz.message.MessageAdmin;
import com.biz.message.QueueDefinition;
import com.biz.message.QueueParser;
import com.google.common.base.Objects;
import org.codelogger.utils.ValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;

/**
 * 支持amqp协议的消息中间件管理员
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 */
public class AmqpMessageAdmin implements MessageAdmin {

    public static final String DEFAULT_DIRECT_EXCHANGE_NAME = "com.biz.base.direct";
    public static final String DEFAULT_TOPIC_EXCHANGE_NAME = "com.biz.base.topic";

    private AmqpAdmin admin;
    private Logger logger = LoggerFactory.getLogger(getClass());

    private DirectExchange defaultDirectExchange;
    private TopicExchange defaultTopicExchange;

    @Override
    public void createQueueIfNecessary(QueueDefinition queueDefinition, Integer maxRetry, Long retryElapse) {
        if (!queueDefinition.isAutomaticCreation()) return;
        QueueParser parser = new QueueParser(queueDefinition);
        Queue queue = declareQueue(parser, maxRetry, retryElapse);
        Exchange exchange = declareExchange(parser, maxRetry, retryElapse);
        declareBinding(parser, queue, exchange, maxRetry, retryElapse);
    }

    @Override
    public void declareBinding(QueueParser parser, Queue queue, Exchange exchange, Integer maxRetry, Long retryElapse) {
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(parser.getRoutingKey()).noargs();
        try {
            admin.declareBinding(binding);
        } catch (org.springframework.amqp.AmqpConnectException e) {
            if (ValueUtils.getValue(maxRetry) == 0) {
                throw new RuntimeException("启动RabbitMQ创建绑定关系失败");
            }
            logger.warn("创建Amqp连接失败, 重试创建连接次数[{}], 重试间隔[{}]ms", ValueUtils.getValue(maxRetry), ValueUtils.getValue(retryElapse), e, ValueUtils.getValue(maxRetry), ValueUtils.getValue(retryElapse), e);
            if (ValueUtils.getValue(maxRetry) > 0) {
                if (ValueUtils.getValue(retryElapse) > 0) {
                    try {
                        Thread.sleep(retryElapse);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    this.declareBinding(parser, queue, exchange, --maxRetry, retryElapse);
                }
            }

        }
        logger.debug("binding queue = {}", binding);
    }

    @Override
    public Exchange declareExchange(QueueParser parser, Integer maxRetry, Long retryElapse) {
        Exchange exchange = createExchange(parser, maxRetry, retryElapse);
        try {
            admin.declareExchange(exchange);
        } catch (org.springframework.amqp.AmqpConnectException e) {
            if (ValueUtils.getValue(maxRetry) == 0) {
                throw new RuntimeException("启动RabbitMQ创建交换器失败");
            }
            logger.warn("创建Amqp连接失败, 重试创建连接次数[{}], 重试间隔[{}]ms", ValueUtils.getValue(maxRetry), ValueUtils.getValue(retryElapse), e);
            if (ValueUtils.getValue(maxRetry) > 0) {
                if (ValueUtils.getValue(retryElapse) > 0) {
                    try {
                        Thread.sleep(retryElapse);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    this.declareExchange(parser, --maxRetry, retryElapse);
                }
            }

        }
        logger.debug("declare exchange = {}", exchange);
        return exchange;
    }

    @Override
    public Queue declareQueue(QueueParser parser, Integer maxRetry, Long retryElapse) {
        return this.declareQueue(parser, null, maxRetry, retryElapse);
    }

    @Override
    public Queue declareQueue(QueueParser parser, Boolean queueDurable, Integer maxRetry, Long retryElapse) {
        if (java.util.Objects.isNull(queueDurable)) {
            queueDurable = true;
        }
        Queue queue = new Queue(parser.getName(), queueDurable, false, false);
        try {
            admin.declareQueue(queue);
        } catch (org.springframework.amqp.AmqpConnectException e) {
            if (ValueUtils.getValue(maxRetry) == 0) {
                throw new RuntimeException("启动RabbitMQ创建队列失败");
            }
            logger.warn("创建Amqp连接失败, 重试创建连接次数[{}], 重试间隔[{}]ms", ValueUtils.getValue(maxRetry), ValueUtils.getValue(retryElapse), e);
            if (ValueUtils.getValue(maxRetry) > 0) {
                if (ValueUtils.getValue(retryElapse) > 0) {
                    try {
                        Thread.sleep(retryElapse);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    this.declareQueue(parser, --maxRetry, retryElapse);
                }
            }
        }
        logger.debug("declare queue = {}", queue);
        return queue;
    }

    private Exchange createExchange(QueueParser parser, Integer maxRetry, Long retryElapse) {
        AmqpExchangeType exchangeType = parser.getExchangeType();
        String exchangeName = parser.getExchangeName();
        switch (exchangeType) {
            case DIRECT:
                if (Objects.equal(exchangeName, DEFAULT_DIRECT_EXCHANGE_NAME)) {
                    return createDefaultDirectExchange(maxRetry, retryElapse);
                }
                return new DirectExchange(parser.getExchangeName());
            case TOPIC:
                if (Objects.equal(exchangeName, DEFAULT_TOPIC_EXCHANGE_NAME)) {
                    return createDefaultDirectExchange(maxRetry, retryElapse);
                }
                return new TopicExchange(parser.getExchangeName());
            default:
                throw new SystemException("暂不支持的交换机类型:" + exchangeType);
        }
    }

    private DirectExchange createDefaultDirectExchange(Integer maxRetry, Long retryElapse) {
        if (defaultDirectExchange == null) {
            defaultDirectExchange = new DirectExchange(DEFAULT_DIRECT_EXCHANGE_NAME, true, false);
            try {
                admin.declareExchange(defaultDirectExchange);
            } catch (org.springframework.amqp.AmqpConnectException e) {
                if (ValueUtils.getValue(maxRetry) == 0) {
                    throw new RuntimeException("启动RabbitMQ创建默认交换器失败");
                }

                logger.warn("创建Amqp连接失败, 重试创建连接次数[{}], 重试间隔[{}]ms", ValueUtils.getValue(maxRetry), ValueUtils.getValue(retryElapse), e, ValueUtils.getValue(maxRetry), ValueUtils.getValue(retryElapse), e);
                if (ValueUtils.getValue(maxRetry) > 0) {
                    if (ValueUtils.getValue(retryElapse) > 0) {
                        try {
                            Thread.sleep(retryElapse);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        this.createDefaultDirectExchange(--maxRetry, retryElapse);
                    }
                }

            }
        }
        return defaultDirectExchange;
    }

    private TopicExchange createDefaultTopicExchange() {
        if (defaultTopicExchange == null) {
            defaultTopicExchange = new TopicExchange(DEFAULT_TOPIC_EXCHANGE_NAME, true, false);
            admin.declareExchange(defaultTopicExchange);
        }
        return defaultTopicExchange;
    }

    public AmqpAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(AmqpAdmin admin) {
        this.admin = admin;
    }


}
