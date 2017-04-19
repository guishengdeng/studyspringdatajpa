package com.biz.gbck.enums.oms;

import com.biz.core.enums.EnumerableValue;

/**
 * 未通过校验的类型
 *
 * @author zhangcheng
 * @date 2017/1/7
 * @reviewer
 * @see
 */
public enum InvalidOmsMessageType implements EnumerableValue {

    /**
     * 有关门店未通过校验的类型
     */
    DEPOT_CODE_EMPTY("门店中台编码为空", 1001),
    DEPOT_LONGITUDE_LATITUDE_INVALID("经纬度不无效", 1002),
    DEPOT_PROVINCE_INVALID("省为无效", 1003),
    DEPOT_CITY_INVALID("市为无效", 1004),
    DEPOT_DISTRICT_INVALID("区为无效", 1005),
    DEPOT_NAME_INVALID("门店名称无效", 1006),
    DEPOT_ADDRESS_INVALID("门店详细地址无效", 1007),
    DEPOT_STATUS_INVALID("门店状态无效", 1008),
    DEPOT_BUSINESS_TIME_INVALID("门店营业时间无效", 1009),

    /**
     * 有关员工未通过校验的类型
     */
    EMPLOYEE_CODE_EMPTY("员工中台编码为空", 2001),

    /**
     * 有关会员未通过校验的类型
     */
    MEMBER_CODE_EMPTY("会员中台编码为空", 3001),

    /**
     * 有关Geo未通过校验的类型
     */
    GEO_PROVINCE_ID_EMPTY("Geo省id为空", 4001),
    GEO_PROVINCE_BAIDU_NAME_INVALID("Geo省百度地理名称无效", 4002),
    GEO_CITY_ID_NULL_EMPTY("Geo市id为空", 4003),
    GEO_CITY_BAIDU_NAME_INVALID("Geo市百度地理名称无效", 4004),
    GEO_DISTRICT_ID_EMPTY("Geo区id为空", 4005),
    GEO_DISTRICT_BAIDU_NAME_INVALID("Geo区百度地理名称无效", 4006),

    /**
     * 有关省仓未通过校验的类型
     */
    WAREHOUSE_CODE_EMPTY("省仓中台编码为空", 5001),
    WAREHOUSE_NAME_INVALID("省仓名称无效", 5002),
    WAREHOUSE_COMPANY_NAME_INVALID("省仓公司名称无效", 5003),
    WAREHOUSE_STATUS_INVALID("省仓状态无效", 5004),

    /**
     * 有关商品未通过校验的类型
     */
    PRODUCT_CODE_EMPTY("商品中台编码为空", 6001),
    PRODUCT_DESCRIPTION_INVALID("商品描述无效", 6002),
    PRODUCT_MAIN_BAR_CODE_INVALID("商品主条码无效", 6003),
    PRODUCT_BIG_CATEGORY_INVALID("商品大类编码无效", 6004),
    PRODUCT_SMALL_CATEGORY_INVALID("商品小类编码无效", 6005),
    PRODUCT_GROSS_PROFIT_INVALID("商品毛利份数无效", 6006),

    /**
     * 有关库存未通过校验的类型
     */
    STOCK_PRODUCT_CODE_EMPTY("库存商品编码为空", 7001),
    STOCK_DEPOT_CODE_EMPTY("库存门店编码为空", 7002),
    STOCK_ALL_PRODUCT_CODE_EMPTY("全量库存商品编码为空", 7004),
    STOCK_ALL_DEPOT_CODE_EMPTY("全量库存门店编码为空", 7005),
    STOCK_ALL_TYPE_INVALID("全量库存类型无效", 7006),

    /**
     * 有关价格未通过校验的类型
     */
    PRICE_PRODUCT_CODE_EMPTY("价格商品编码为空", 8001),
    PRICE_PRODUCT_NAME_INVALID("价格商品名称无效", 8002),
    PRICE_AREA_INVALID("价格区域无效", 8003),
    PRICE_RETAIL_PRICE_INVALID("零售价无效", 8004),
    PRICE_COST_PRICE_INVALID("成本价无效", 8005),

    /**
     * 有关促销未通过校验的类型
     */
    PROMOTION_TYPE_INVALID("促销类型无效", 9001),
    PROMOTION_GOODS_CODE_INVALID("促销商品编码无效", 9002),
    PROMOTION_GOODS_NAME_INVALID("促销商品名称无效", 9003),
    PROMOTION_DEPOT_CODE_INVALID("促销门店编码无效", 9004),
    PROMOTION_DEPOT_NAME_INVALID("促销门店名称无效", 9005),
    PROMOTION_TIME_INVALID("促销时间无效", 9006),
    PROMOTION_PRICE_INVALID("促销价格无效", 9007),

    /**
     * 有关组合商品未通过校验的类型
     */
    GROUP_PRODUCT_CODE_EMPTY("中台组合商品编码无效", 10001),
    GROUP_PRODUCT_DESCRIPTION_INVALID("中台组合商品描述无效", 10002),
    GROUP_PRODUCT_ITEM_INVALID("中台组合商品明细无效", 10005),
    GROUP_PRODUCT_ITEM_NONE_EXISTENCE("中台组合商品下的明细商品在本地不存在", 10006);

    private int value;

    private String description;

    InvalidOmsMessageType(String description, Integer value) {
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
}
