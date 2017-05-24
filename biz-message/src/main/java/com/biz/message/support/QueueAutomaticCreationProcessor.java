package com.biz.message.support;


import com.biz.core.asserts.SystemAsserts;
import com.biz.core.exceptions.SystemException;
import com.biz.message.MessageAdmin;
import com.biz.message.QueueDefinition;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.util.ClassUtils;

/**
 * 自动创建消息队列处理器,可以单独使用,需要
 *
 * @author yanweijin
 * @usage 配置为spring管理的bean即可;<br> 在单独使用的场合,需要通过配置来添加指定的类为队列定义枚举,见{@link
 * #setQueueDefinitionEnumClasses}
 * @reviewer
 * @since 2016年7月31日
 */
public class QueueAutomaticCreationProcessor implements SmartInitializingSingleton, BeanFactoryAware {
    private BeanFactory beanFactory;
    private String messageAdminBeanName = "messageAdmin";
    private MessageAdmin messageAdmin;
    private Set<Class<Enum<?>>> queueDefinitionEnumClasses = Sets.newHashSet();
    private Logger logger = LoggerFactory.getLogger(getClass());

    private Integer maxRetry;

    private Long retryElapse;

    public QueueAutomaticCreationProcessor(Integer maxRetry, Long retryElapse) {
        this.maxRetry = maxRetry;
        this.retryElapse = retryElapse;
    }

    @Override
    public void afterSingletonsInstantiated() {
        logger.info("自动创建队列激活");
        this.queueDefinitionEnumClasses.addAll(QueueDefinitionScanFilter.container);
        List<QueueDefinition> definitions = Lists.newArrayList();
        for (Class<Enum<?>> clz : queueDefinitionEnumClasses) {
            Enum<?>[] enums = clz.getEnumConstants();
            for (Enum<?> e : enums) {
                definitions.add((QueueDefinition) e);
            }
        }
        MessageAdmin admin = findMessageAdmin();
        for (QueueDefinition def : definitions) {
           /* admin.createQueueIfNecessary(def, this.maxRetry, this.retryElapse);*/ // TODO: 17-5-19 dylan 开放会有异常等待解决
        }
    }

    private MessageAdmin findMessageAdmin() {
        if (this.messageAdmin != null) return this.messageAdmin;
        logger.debug("没有设置messageAdmin,根据bean name来寻找对应bean,bean name = {}", messageAdminBeanName);
        SystemAsserts.hasText(messageAdminBeanName, "messageAdminBeanName必须有值");
        MessageAdmin bean = beanFactory.getBean(messageAdminBeanName, MessageAdmin.class);
        SystemAsserts.notNull(bean, "messageAdmin不能为null");
        return bean;
    }


    public String getMessageAdminBeanName() {
        return messageAdminBeanName;
    }

    public void setMessageAdminBeanName(String messageAdminBeanName) {
        this.messageAdminBeanName = messageAdminBeanName;
    }


    /**
     * 通过spring配置受管理的枚举类,xml配置方式:
     *
     * <pre>
     * &lt;property name="queueDefinitionEnumClasses">
     * 	&lt;list>
     * 		&lt;value>com.biz.base.BizBaseQueue&lt;/value>
     * 		&lt;value>com.biz.test.TestQueue&lt;/value>
     * 	&lt;/list>
     * &lt;/property>
     * </pre>
     *
     * @param clzNames 需要自动创建的队列所在className list
     * @author yanweijin
     * @date 2016年7月31日
     */
    @SuppressWarnings("unchecked")
    public void setQueueDefinitionEnumClasses(List<String> clzNames) {
        try {
            for (String clzName : clzNames) {
                Class<?> clz = ClassUtils.forName(clzName, this.getClass().getClassLoader());
                SystemAsserts.isTrue(Enum.class.isAssignableFrom(clz), "队列定义类必须是一个枚举");
                SystemAsserts.isTrue(QueueDefinition.class.isAssignableFrom(clz), "队列定义类必须实现QueueDefinition接口");
                queueDefinitionEnumClasses.add((Class<Enum<?>>) clz);
            }
        } catch (ClassNotFoundException | LinkageError e) {
            throw new SystemException(e);
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public MessageAdmin getMessageAdmin() {
        return messageAdmin;
    }

    public void setMessageAdmin(MessageAdmin messageAdmin) {
        this.messageAdmin = messageAdmin;
    }

}
