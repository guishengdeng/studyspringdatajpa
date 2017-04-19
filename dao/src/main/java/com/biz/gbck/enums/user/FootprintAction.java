package com.biz.gbck.enums.user;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum FootprintAction implements EnumerableValue {
    VIEW(0, "浏览商品"),
    COLLECT(5, "收藏商品"),
    ADD_TO_CART(10, "添加到购物车"),
    DELETE_FROM_CART(15, "从购物车删除"),;

    private int value;
    private String desc;
    private FootprintAction(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class Converter extends BaseEnumValueConverter<FootprintAction> {
    }


}
