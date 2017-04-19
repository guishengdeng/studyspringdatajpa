package com.biz.gbck.enums.oms;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 未通过校验的Vo类型
 *
 * @author zhangcheng
 * @date 2017/1/7
 * @reviewer
 * @see
 */
public enum InvalidOmsDataType implements EnumerableValue {

    OMS_DEPOT(10001, "中台门店Vo"),
    OMS_STOCK(10002, "中台增量库存Vo"),
    OMS_MEMBER(10003, "中台会员Vo"),
    OMS_WAREHOUSE(10004, "中台省仓Vo"),
    OMS_EMPLOYEE(10005, "中台员工Vo"),
    OMS_GEO(10006, "中台GeoVo"),
    OMS_PRODUCT(10007, "中台商品Vo"),
    OMS_PRICE(10008, "中台价格Vo"),
    OMS_DEPOT_PROMOTION(10009, "中台门店增量促销Vo"),
    OMS_ALL_DEPOT_PROMOTION(10010, "中台门店全量促销Vo"),
    OMS_STOCK_ALL(10011, "中台全量库存Vo"),
    OMS_PRICE_ALL(10012, "中台全量价格Vo"),
    OMS_GROUP_PRODUCT(10013, "中台组合商品Vo");

    private int value;

    private String description;

    InvalidOmsDataType(int value, String description) {
        this.value = value;
        this.description = description;
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

    public static class Converter extends BaseEnumValueConverter<InvalidOmsDataType> {
    }
}
