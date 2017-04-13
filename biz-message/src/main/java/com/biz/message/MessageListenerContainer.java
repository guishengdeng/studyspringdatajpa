package com.biz.message;


import com.biz.message.converter.MessageConverter;

public interface MessageListenerContainer {
    <T> void addMessageListener(MessageListener<T> messageListener);

    void start();

    void setMessageConverter(MessageConverter messageConverter);
}
