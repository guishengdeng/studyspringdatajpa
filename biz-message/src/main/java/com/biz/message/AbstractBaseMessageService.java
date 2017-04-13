package com.biz.message;


import com.biz.core.asserts.SystemAsserts;
import com.biz.message.converter.DefaultMessageConverter;
import com.biz.message.converter.MessageConverter;
import com.biz.message.mo.AmqpSendStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBaseMessageService implements MessageService {

    protected MessageConverter messageConverter = new DefaultMessageConverter();
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void setMessageConverter(MessageConverter converter) {
        this.messageConverter = converter;
    }

    public MessageConverter getMessageConverter() {
        SystemAsserts.notNull(messageConverter, "messageConverter不能为null");
        return messageConverter;
    }


    @Override
    public <T> T receiveMessage(QueueDefinition queueDefinition, Class<T> msgType) {
        return receive(queueDefinition, msgType);
    }

    @Override
    public AmqpSendStatus sendMessage(QueueDefinition queueDefinition, BizMessage<?> message) {
        return send(queueDefinition, message);
    }


    /**
     * 子类实现该方法来从指定队列接收消息,预先抽象
     *
     * @author yanweijin
     * @date 2016年7月24日
     */
    protected abstract <T> T receive(QueueDefinition queueDefinition, Class<T> msgType);

    /**
     * 子类实现该方法来向目标队列发送消息,预先抽象
     *
     * @author yanweijin
     * @date 2016年7月24日
     */
    protected abstract AmqpSendStatus send(QueueDefinition queueDefinition, BizMessage<?> message);

}
