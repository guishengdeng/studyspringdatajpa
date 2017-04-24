package com.biz.gbck.enums.vendor;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum CertificateType implements EnumerableValue {

    ID_CARD(0), //身份证
    PASSPORT(1); //护照

    private Integer value;

    CertificateType(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static class Converter extends BaseEnumValueConverter<CertificateType> {

    }


}
