package com.biz.gbck.dao.mysql.po.voucher;

import com.biz.gbck.common.NameAndValueAndDescriptionAbleEnum;
import com.biz.gbck.dao.mysql.po.NameAndValueAndDescriptionAbleEnumConverter;
/**
 * 
 * @author yang
 *
 */
public enum VoucherTypeStatus implements NameAndValueAndDescriptionAbleEnum {

    OVERLAB("叠加", 0),

    MUTEX("互斥", 1);

    private String name;

    private Integer value;

    VoucherTypeStatus(String name, Integer value) {
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

    public static class VoucherTypeStatusConverter
        extends NameAndValueAndDescriptionAbleEnumConverter<VoucherTypeStatus> {
    }
}
