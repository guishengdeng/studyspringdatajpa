package com.biz.message.converter;

import com.biz.core.exceptions.SystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

;

public class DefaultMessageConverter implements MessageConverter {

    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public String toMessageString(Object msg) {
        try {
            return mapper.writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

    @Override
    public <T> T fromMessageString(String msgString, Class<T> clz) {
        try {
            return mapper.reader(clz).readValue(msgString);
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

}
