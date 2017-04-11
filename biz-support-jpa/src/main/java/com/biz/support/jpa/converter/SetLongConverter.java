package com.biz.support.jpa.converter;

/**
 * @author yanweijin
 * @date 2016/12/23
 */
public class SetLongConverter extends AbstractSetStringConverter<Long> {
    @Override
    protected Long string2Element(String data) {
        return Long.valueOf(data);
    }
}
