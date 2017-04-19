package com.biz.gbck.enums.user;


import com.biz.core.enums.EnumerableValue;

/**
 * 账户安全信息等级
 * Created by JKLiues on 2017/2/19.
 */
public enum SecurityLevel implements EnumerableValue {
    LOWER(0, "较低"), MIDDLE(1, "普通"), HIGH(2, "非常高");

    private int value;
    private String desc;

    SecurityLevel(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
