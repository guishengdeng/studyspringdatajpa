package com.biz.gbck.dao.mysql.po.voucher;

import com.biz.gbck.common.NameAndValueAndDescriptionAbleEnum;
import com.biz.gbck.dao.mysql.po.NameAndValueAndDescriptionAbleEnumConverter;

/**
 * 优惠券使用特点
 */
public enum VoucherLimitType implements NameAndValueAndDescriptionAbleEnum {

    NONE("无限制", 1),

    BY_CATEGORY("按分类", 2),

    BY_PRODUCTS("按商品", 3);


    private Integer value;

    private String name;

    VoucherLimitType(String name, Integer value) {
        this.value = value;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDescription() {
        return name;
    }

    public static class VoucherLimitTypeConverter
        extends NameAndValueAndDescriptionAbleEnumConverter<VoucherLimitType> {
    }
}
