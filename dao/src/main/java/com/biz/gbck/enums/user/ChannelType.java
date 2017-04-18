package com.biz.gbck.enums.user;

import com.biz.core.enums.EnumerableValue;

/**
 * 渠道
 *
 * @author lei
 * @date 2017年02月10日
 * @reviewer
 * @see
 */
public enum ChannelType implements EnumerableValue {

    BBC(1, "www.1919.cn");

    ChannelType(Integer value, String code) {
        this.value = value;
        this.code = code;
    }

    private Integer value;

    private String code;

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
