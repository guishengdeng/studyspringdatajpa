package com.biz.gbck.enums.product;

import com.biz.core.enums.EnumerableValue;

/**
 * 商品展示状态
 *
 * @author lei
 * @date 2016年01月16日
 * @reviewer
 * @see
 */
public enum ProductShowStatus implements EnumerableValue {

    NORMAL(10), //正常
    OFF_SALE(0), //下架
    ;

    private int value;

    ProductShowStatus(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
