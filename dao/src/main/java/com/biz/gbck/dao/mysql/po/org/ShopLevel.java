package com.biz.gbck.dao.mysql.po.org;


import com.biz.gbck.common.NameAndValueAndDescriptionAbleEnum;
import com.biz.gbck.dao.mysql.po.NameAndValueAndDescriptionAbleEnumConverter;

/**
 * 记录是否为用户导入
 * Created by dylan on 16-7-21.
 */
public enum ShopLevel implements NameAndValueAndDescriptionAbleEnum {

    VIP_ALL("所有会员", -1),

    VIP_1("1倍会员", 1),

    VIP_15("15倍会员", 15),

    VIP_20("20倍会员", 20);

    private String name;

    private Integer value;

    ShopLevel(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override public String getName() {
        return name;
    }

    @Override public Integer getValue() {
        return value;
    }

    @Override public String getDescription() {
        return name;
    }

    public static class Converter
        extends NameAndValueAndDescriptionAbleEnumConverter<ShopLevel> {
    }
}
