package com.biz.gbck.common;

import com.biz.gbck.enums.product.GeoLevelEnum;

/**
 * SOA 常量定义
 *
 * @author david-liu
 * @date 2017年01月07日
 * @reviewer
 * @see
 */
public class Constant {

    /**
     * 默认的搜索页数
     */
    public static final Integer DEFAULT_PAGE = 0;

    /**
     * 默认的页大小
     */
    public static final Integer DEFAULT_PAGE_SIZE = 16;

    /**
     * 默认用户等级
     */
    public static final Integer DEFAULT_USER_LEVEL = 1;

    /**
     * 搜索结果不分页标识
     */
    public static final Integer NO_PAGE_FLAG = -1;

    /**
     * Geo 全国 ID(GeoLevel 为 GeoLevel.GEO_PROVINCE)
     */
    public static final Long ALL_PROVINCE_GEO_ID = -1L;

    /**
     * 商品为全国统一销售时的 GeoLevel
     */
    public static final GeoLevelEnum ALL_PROVINCE_GEO_LEVEL = GeoLevelEnum.GEO_PROVINCE;

    /**
     * Redis hash quantity field
     */
    public static final String RO_ID_FIELD = "id";

    public static final String RO_QUANTITY_FIELD = "quantity";

    public static final String RO_UPDATE_TIMESTAMP_FIELD = "updateTimestamp";

    public static final String SEPARATOR = ":";

    /**
     * 全部B类商品编码集合key
     */
    public static final String TYPE_B_PRODUCT_CODES_SET_KEY = "BProductCodesSetKey";
}
