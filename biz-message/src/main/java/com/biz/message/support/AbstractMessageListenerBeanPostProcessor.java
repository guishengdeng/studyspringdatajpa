package com.biz.message.support;

import com.biz.message.MessageListener;
import com.google.common.collect.Lists;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 初始化消息监听器bean的抽象处理器
 *
 * @author yanweijin
 * @usage 被继承后, 提供不同方案(rabbit, rocket...)的消息监听器对接方式
 * @reviewer
 * @since 2016年7月31日
 */
public abstract class AbstractMessageListenerBeanPostProcessor implements BeanPostProcessor {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected List<MessageListener<?>> listenerContainer = Lists.newArrayList();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MessageListener) {
            listenerContainer.add((MessageListener<?>) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


}
