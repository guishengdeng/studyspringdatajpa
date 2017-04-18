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

    NORMAL(0), //正常
    OFF_SALE(1), //下架
    NOT_IN_SALE_AREA(2), //不在销售区域
    INSUFFICIENT_STOCK(3), //库存不足
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
