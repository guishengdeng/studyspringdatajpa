package com.biz.gbck.enums;

import com.biz.core.enums.EnumerableValue;

/**
 * 通用状态枚举(标识可用还是不可用)
 *
 * @author david-liu
 * @date 2017年04月07日
 * @reviewer
 */
public enum CommonStatusEnum implements EnumerableValue {

    ENABLE(1),
    DISABLE(0);

    private int value;


    CommonStatusEnum(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public boolean isEnable() {
        return this.value == 1;
    }
}
