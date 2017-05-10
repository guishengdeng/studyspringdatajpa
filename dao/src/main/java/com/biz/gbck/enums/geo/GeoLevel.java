package com.biz.gbck.enums.geo;


import com.biz.core.enums.EnumerableValue;

/**
 * 区域商品划分等级
 *
 * @author david-liu
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
public enum GeoLevel implements EnumerableValue {

    GEO_CITY(1), GEO_PROVINCE(2), GEO_DISTRICT(3), DOMAIN_SALE_AREA(4);
//
//    public static class Converter extends BaseEnumValueConverter<GeoLevel> {
//    }

    GeoLevel(Integer value) {
        this.value = value;
    }

    private Integer value;

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
