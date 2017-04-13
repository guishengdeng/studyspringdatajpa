package com.biz.message.converter;

public interface MessageConverter {
    String toMessageString(Object msg);

    <T> T fromMessageString(String msgString, Class<T> clz);
}
