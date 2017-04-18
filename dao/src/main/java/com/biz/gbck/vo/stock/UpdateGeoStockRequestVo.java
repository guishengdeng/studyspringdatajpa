package com.biz.gbck.vo.stock;

import com.biz.gbck.enums.product.GeoLevelEnum;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 更新区域库存请求vo
 *
 * @author lei
 */
public class UpdateGeoStockRequestVo implements Serializable {
    private static final long serialVersionUID = 4614073321383237624L;

    /**
     * 商品编码(必选)
     */
    private String productCode;

    /**
     * 省市Id(必选)
     */
    private Integer geoId;

    /**
     * 库存更新数量(正数即加库存, 负数即减库存)
     */
    private int quantity = 0;

    /**
     * 级别(默认:省级, 可选, 当前有且只可选省级!)
     */
    private GeoLevelEnum geoLevel = GeoLevelEnum.GEO_PROVINCE;

    public UpdateGeoStockRequestVo(String productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getGeoId() {
        return geoId;
    }

    public void setGeoId(Integer geoId) {
        this.geoId = geoId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public GeoLevelEnum getGeoLevel() {
        return geoLevel;
    }

    public void setGeoLevel(GeoLevelEnum geoLevel) {
        this.geoLevel = geoLevel;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
