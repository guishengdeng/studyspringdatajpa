package com.biz.soa.promotion.config;

import com.biz.core.event.BizEventMulticaster;
import com.biz.core.event.BizEventPublisher;
import com.biz.core.transaction.BizTransactionManager;
import com.biz.message.MessageAdmin;
import com.biz.message.QueueDefinition;
import com.biz.message.QueueParser;
import com.biz.service.IdService;
import com.biz.soa.promotion.amqp.queue.PromotionQueueDefinition;
import com.biz.soa.promotion.zookeeper.PromotionAmqpQueueIndexFactory;
import java.beans.PropertyVetoException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;

/**
 * 促销服务Config
 *
 * Created by david-liu on 2017/04/24 15:11.
 */
@Configuration
public class PromotionConfig {

    private Environment environment;

    private MessageAdmin messageAdmin;

    public PromotionConfig(@Autowired Environment environment, @Autowired MessageAdmin messageAdmin) {
        this.environment = environment;
        this.messageAdmin = messageAdmin;
    }

    @Bean
    public IdService idService() {
        Integer soaIdx = Integer.valueOf(environment.getProperty("biz.soa.idx"));
        String soaIdxZooNodePath = environment.getProperty("biz.zookeeper.soaIdx.path");
        String zookeeperUrl = environment.getProperty("biz.zookeeper.url");
        return new IdService(soaIdx, soaIdxZooNodePath, zookeeperUrl);
    }

    @Bean
    public BizEventMulticaster bizEventMulticaster() {
        return new BizEventMulticaster();
    }

    @Bean
    public BizEventPublisher bizEventPublisher() {
        return new BizEventPublisher();
    }

    @Bean
    public JpaTransactionManager transactionManager(@Autowired BizEventPublisher bizEventPublisher) throws PropertyVetoException {
        BizTransactionManager jpaTransactionManager = new BizTransactionManager();
        jpaTransactionManager.setEventPublisher(bizEventPublisher);
        return jpaTransactionManager;
    }

    @Bean
    public QueueDefinition initAmqpPromotionEnvironment() throws UnknownHostException {
        Integer amqpMaxRetry = environment.getProperty("biz.amqp.maxRetry", Integer.class, 3);
        Long amqpRetryElapse = environment.getProperty("biz.amqp.retryElapse", Long.class, 1000L);
        String hostAddress = Inet4Address.getLocalHost().getHostAddress();
        String promotionExchange = environment.getProperty("biz.amqp.promotion.exchange", "biz.rmq.promotion");
        String promotionQueuePrefix = environment.getProperty("biz.amqp.promotion.queue.prefix", "soaPromotion");
        String promotionQueueZookeeperPath = String.format("%s%s%s", environment.getProperty("biz.amqp.promotion.queue.zookeeper.path"), PromotionAmqpQueueIndexFactory.NODE_SEPARATOR, hostAddress);
        String zookeeperUrl = environment.getProperty("biz.zookeeper.url");
        Integer promotionQueueIdx = PromotionAmqpQueueIndexFactory.newInstance(promotionQueueZookeeperPath, zookeeperUrl).getAmqpQueueIdx();
        String promotionQueueName = String.format("%s_%s_%s", promotionQueuePrefix, hostAddress, promotionQueueIdx);
        PromotionQueueDefinition queueDefinition = new PromotionQueueDefinition(promotionExchange, promotionQueueName, promotionQueuePrefix);
        QueueParser queueParser = new QueueParser(queueDefinition);
        Queue queue = messageAdmin.declareQueue(queueParser, false, amqpMaxRetry, amqpRetryElapse);
        Exchange exchange = messageAdmin.declareExchange(queueParser, amqpMaxRetry, amqpRetryElapse);
        messageAdmin.declareBinding(queueParser, queue, exchange, amqpMaxRetry, amqpRetryElapse);
        return queueDefinition;
    }
}
