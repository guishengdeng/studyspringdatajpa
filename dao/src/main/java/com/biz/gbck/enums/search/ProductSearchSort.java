package com.biz.gbck.enums.search;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品搜索排序枚举
 *
 * @author david-liu
 * @date 2017年01月07日
 * @reviewer
 * @see
 */
public enum ProductSearchSort implements Serializable {
    // 综合排序
    DEFAULT_SORT("defaultSort"),
    // 销量降序
    SALES_VOLUME_DESC("salesVolumeDesc"),
    // 销量升序
    SALES_VOLUME_ASC("salesVolumeAsc"),
    // 价格升序
    SALE_PRICE_DESC("salePriceDesc"),
    // 价格降序
    SALE_PRICE_ASC("salePriceAsc"),
    // 上架时间升序
    ON_SALE_TIME_ASC("onSaleTimeAsc"),
    // 上架时间降序
    ON_SALE_TIME_DESC("onSaleTimeDesc");
    /**
     * 搜索排序描述信息
     */
    private String description;

    ProductSearchSort(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
