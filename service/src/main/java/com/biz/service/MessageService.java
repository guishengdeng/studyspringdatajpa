package com.biz.service;


import ch.qos.logback.classic.pattern.MessageConverter;
import com.biz.message.BizMessage;
import com.biz.message.QueueDefinition;
import com.biz.message.converter.DefaultMessageConverter;
import com.biz.message.mo.AmqpSendStatus;

/**
 * 消息服务接口,用于收/发消息
 *
 * @author yanweijin
 * @usage 在spring配置实现类和即可使用
 * @reviewer
 * @since 2016年7月24日
 */
public interface MessageService {

    /**
     * 向指定队列发送消息,消息将被转换器转换,这是一个同步操作
     *
     * @param message 消息数据对象
     * @author yanweijin
     * @date 2016年7月24日
     */
    AmqpSendStatus sendMessage(QueueDefinition queueDefinition, BizMessage<?> message);

    /**
     * 从指定队列接收消息,并转换为对应的类型,这是一个同步操作
     *
     * @return 转换后的消息
     * @author yanweijin
     * @date 2016年7月24日
     */
    <T> T receiveMessage(QueueDefinition queueDefinition, Class<T> msgType);

    /**
     * 设置消息转换器,默认为{@link DefaultMessageConverter}
     *
     * @author yanweijin
     * @date 2016年7月24日
     */
    void setMessageConverter(MessageConverter converter);
}
