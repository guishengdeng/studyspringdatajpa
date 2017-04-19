package com.biz.gbck.enums.product;

import com.biz.core.enums.EnumerableValue;

/**
 * 区域商品划分等级
 *
 * @author david-liu
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
public enum GeoLevelEnum implements EnumerableValue {

    GEO_CITY(1), GEO_PROVINCE(2), GEO_DISTRICT(3), DOMAIN_SALE_AREA(4);

    private Integer value;

    GeoLevelEnum(Integer value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
