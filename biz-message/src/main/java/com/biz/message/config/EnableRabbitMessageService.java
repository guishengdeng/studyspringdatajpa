package com.biz.message.config;

import com.biz.message.MessageAdmin;
import com.biz.message.MessageService;
import com.biz.message.amqp.AmqpMessageAdmin;
import com.biz.message.amqp.AmqpMessageService;
import com.biz.message.support.AmqpMessageListenerBeanPostProcessor;
import com.biz.message.support.QueueAutomaticCreationProcessor;
import com.biz.message.support.QueueDefinitionScanFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;

/**
 * 用于激活amqp message service的自动配置类
 *
 * @author yanweijin
 * @usage 将本类加入spring管理
 * @reviewer
 * @since 2016年8月4日
 */
@Configuration
@EnableRabbit
@ComponentScan(value = {"com.biz.message.queue"},
        includeFilters = @Filter(classes = QueueDefinitionScanFilter.class, type = FilterType.CUSTOM))
public class EnableRabbitMessageService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment environment;

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        logger.debug("AmqpConfiguration.messageConverter --> execute");
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    AmqpMessageListenerBeanPostProcessor messageListenerBeanPostProcessor() {
        logger.info("autoconfig.message:加载消息监听器bean处理器");
        return new AmqpMessageListenerBeanPostProcessor();
    }

    @Bean
    MessageAdmin messageAdmin() {
        logger.info("autoconfig.message:加载消息服务管理员");
        AmqpMessageAdmin admin = new AmqpMessageAdmin();
        admin.setAdmin(admin());
        return admin;
    }

    @Bean
    MessageService messageService() {
        logger.info("autoconfig.message:初始化消息服务");
        AmqpMessageService messageService = new AmqpMessageService();
        messageService.setAmqpTemplate(amqpTemplate());
        messageService.setMaxRetry(this.getMaxRetry());
        messageService.setRetryElapse(this.getRetryElapse());
        return messageService;
    }

    @Bean
    QueueAutomaticCreationProcessor queueAutomaticCreationProcessor() {
        logger.info("autoconfig.message:加载队列自动创建处理器");
        QueueAutomaticCreationProcessor qacp = new QueueAutomaticCreationProcessor(this.getMaxRetry(), this.getRetryElapse());
        qacp.setMessageAdmin(messageAdmin());
        return qacp;
    }

    /**
     * 定义rabbit监听器容器工厂
     *
     * @return SimpleRabbitListenerContainerFactory
     */
    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        logger.debug("AmqpConfiguration.rabbitListenerContainerFactory --> execute");
        logger.debug("amqp link ... username={},password={},host={},port={}", this.getUsername(), this.getPassword(), this.getHost(), this.getPort());
        SimpleRabbitListenerContainerFactory rlc = new SimpleRabbitListenerContainerFactory();
        rlc.setConnectionFactory(rabbitConnectionFactory());
        rlc.setMessageConverter(messageConverter());
        return rlc;
    }

    /**
     * 定义rabbit连接工厂
     *
     * @return ConnectionFactory
     */
    @Bean
    ConnectionFactory rabbitConnectionFactory() {
        String host = this.getHost(), username = this.getUsername(), password = this.getPassword();
        int port = this.getPort();
        logger.debug("AmqpConfiguration.rabbitConnectionFactory--->execute");
        logger.debug("开始初始化spring-amqp消息配置, host:{}, port:{}, username:{}, password{}", host, port, username, password);

        CachingConnectionFactory ccf = new CachingConnectionFactory(host);
        ccf.setPort(port);
        ccf.setUsername(username);
        ccf.setPassword(password);
        return ccf;
    }

    /**
     * 定义rabbit admin账号
     *
     * @return RabbitAdmin
     */
    @Bean
    RabbitAdmin admin() {
        String host = this.getHost(), username = this.getUsername(), password = this.getPassword();
        int port = this.getPort();
        logger.debug("AmqpConfiguration.admin --> execute");
        logger.debug("amqp link ... username={},password={},host={},port={}", username, password, host, port);
        return new RabbitAdmin(rabbitConnectionFactory());
    }

    /**
     * 定义amqp模板
     *
     * @return AmqpTemplate
     */
    @Bean
    AmqpTemplate amqpTemplate() {
        String host = this.getHost(), username = this.getUsername(), password = this.getPassword();
        int port = this.getPort();
        logger.debug("AmqpConfiguration.amqpTemplate --> execute");
        logger.debug("amqp link ... username={},password={},host={},port={}", username, password, host, port);
        RabbitTemplate amqp = new RabbitTemplate(rabbitConnectionFactory());
        amqp.setMessageConverter(messageConverter());
        return amqp;
    }

    private int getPort() {
        return environment.getProperty("biz.amqp.port", Integer.class);
    }

    private String getUsername() {
        return environment.getProperty("biz.amqp.username", String.class);
    }

    private String getPassword() {
        return environment.getProperty("biz.amqp.password", String.class);
    }

    private String getHost() {
        return environment.getProperty("biz.amqp.host", String.class);
    }

    private Integer getMaxRetry() {
        return environment.getProperty("biz.amqp.maxRetry", Integer.class);
    }

    private Long getRetryElapse() {
        return environment.getProperty("biz.amqp.retryElapse", Long.class);
    }
}
