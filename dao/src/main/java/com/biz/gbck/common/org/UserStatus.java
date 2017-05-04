package com.biz.gbck.common.org;

/**
 * 用户状态
 * Created by defei on 3/16/16.
 */
public enum UserStatus {

    /**
     * 禁用
     */
    DISABLED(0),

    /**
     * 正常
     */
    NORMAL(9), ;

    private final Integer value;


    UserStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
