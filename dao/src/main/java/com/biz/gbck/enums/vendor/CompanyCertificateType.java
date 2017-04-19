package com.biz.gbck.enums.vendor;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

public enum CompanyCertificateType implements EnumerableValue {

    ID_CARD(0),
    PASSPORT(1);

    private Integer value;

    CompanyCertificateType(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static class Converter extends BaseEnumValueConverter<CompanyCertificateType> {

    }


}
