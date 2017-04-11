package com.biz.event;


import com.biz.core.asserts.SystemAsserts;
import com.biz.core.exceptions.SystemException;
import com.biz.transaction.BizTransactionManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * 基础框架使用的事件发布器,实现了同时支持发布同步事件和异步事件,不推荐再单独感知spring提供的事件发布器
 * 本类需要配合事务管理器,事件广播同时使用,需要在xml中手动配置bean
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 */
public class BizEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(BizEvent event) {
        SystemAsserts.notNull(event, "发布事件时事件对象不能为null");
        applicationEventPublisher.publishEvent(new AsyncBizEventWrapper(event));
    }

    public void syncPublishEvent(BizEvent event) {
        SystemAsserts.notNull(event, "发布事件时事件对象不能为null");
        applicationEventPublisher.publishEvent(new SyncBizEventWrapper(event));
    }


    public void publishEventUsingTransactionManager(BizEvent event) {
        SystemAsserts.notNull(event, "发布事件时事件对象不能为null");
        try {
            BizTransactionManager.publishEvent(event, true);
        } catch (SystemException e) {
            throw new UnsupportedOperationException("你可能没有使用BizTranactionManager来作为事务管理器", e);
        }
    }

    public void syncPublishEventUsingTransactionManager(BizEvent event) {
        SystemAsserts.notNull(event, "发布事件时事件对象不能为null");
        try {
            BizTransactionManager.publishEvent(event, false);
        } catch (SystemException e) {
            throw new UnsupportedOperationException("你可能没有使用BizTranactionManager来作为事务管理器", e);
        }
    }

}
