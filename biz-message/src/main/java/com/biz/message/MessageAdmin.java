package com.biz.message;

/**
 * 消息服务管理员,用于在代码中执行创建,删除(暂无)等管理操作
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 */
public interface MessageAdmin {

    /**
     * 如果有必要(定义中自动创建字段为true,并且消息队列尚不存在),则创建该定义对应的消息队列
     *
     * @param maxRetry 最多重试次数
     * @param retryElapse 重试间隔 (单位: 毫秒)
     * @author yanweijin
     * @date 2016年7月24日
     */
    void createQueueIfNecessary(QueueDefinition queueDefinition, Integer maxRetry, Long retryElapse);


}
