package com.biz.gbck.dao.mysql.po.voucher;

import com.biz.gbck.common.NameAndValueAndDescriptionAbleEnum;
import com.biz.gbck.dao.mysql.po.NameAndValueAndDescriptionAbleEnumConverter;

/**
 * 优惠券使用特点
 */
public enum VoucherExpireType implements NameAndValueAndDescriptionAbleEnum {

    EXPIRE_BY_DATE_RANGE("按设置的时间段计算过期", 1),

    EXPIRE_BY_PERIOD("领取后X天过期", 2);

    private Integer value;

    private String name;

    VoucherExpireType(String name, Integer value) {
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

    public static class VoucherExpireTypeConverter
        extends NameAndValueAndDescriptionAbleEnumConverter<VoucherExpireType> {
    }
}
