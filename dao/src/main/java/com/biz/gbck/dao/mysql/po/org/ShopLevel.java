package com.biz.gbck.dao.mysql.po.org;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 记录是否为用户导入
 * Created by dylan on 16-7-21.
 */
public enum ShopLevel implements EnumerableValue {

    VIP_ALL("所有会员", -1),

    VIP_1("1倍会员", 1),

    VIP_15("15倍会员", 15),

    VIP_20("20倍会员", 20);

    private String name;

    private int value;

    ShopLevel(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return name;
    }

    public static class Converter
        extends BaseEnumValueConverter<ShopLevel> {
    }
}
