package com.biz.gbck.enums.vendor;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum AuditVendorSearchType implements EnumerableValue {
    OWNER_REALNAME(0),//按店主名称搜索
    ID_CARD(1),//按身份证号搜索
    PHONE_NUBER(2),//按电话号码搜索
    VENDOR_NAME(3);//按店铺名称搜索

    private Integer value;

    private AuditVendorSearchType(Integer value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static class Converter extends BaseEnumValueConverter<AuditStatus> {

    }

}
