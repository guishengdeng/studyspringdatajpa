package com.biz.message;

public interface MessageListener<M> {
    /**
     * 指定要监听的队列
     *
     * @author yanweijin
     * @date 2016年7月29日
     */
    QueueDefinition getListenedQueue();

    /**
     * 监听方法
     *
     * @author yanweijin
     * @date 2016年7月29日
     */
    void onMessage(BizMessage<M> message);
}
