package com.biz.gbck.enums.depot;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * @author zhangcheng
 * @date 2016/12/13
 * @reviewer
 * @see
 */
public enum DepotType implements EnumerableValue {

    ZY("ZY", 1), //直营
    ZG("ZG", 2), //直管
    JM("JM", 3), //加盟
    SC("SC", 4), //省仓
    HH("HH", 5), //城市合伙人
    VT("VT", 10);//虚拟门店


    private int value;
    private String description;

    DepotType(String description, Integer value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static class Converter extends BaseEnumValueConverter<DepotType> {
    }
}
