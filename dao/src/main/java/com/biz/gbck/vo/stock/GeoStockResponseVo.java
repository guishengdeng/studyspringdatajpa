package com.biz.gbck.vo.stock;

import com.biz.gbck.enums.product.GeoLevelEnum;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * geo库存 返回vo
 *
 * @author lei
 */
public class GeoStockResponseVo implements Serializable {
    private static final long serialVersionUID = -1940307064652812436L;
    /**
     * 省、市Id
     */
    private Integer geoId;

    /**
     * 省市级别
     */
    private GeoLevelEnum geoLevel = GeoLevelEnum.GEO_PROVINCE;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 是否返回对应省、市全省库存
     */
    private Boolean isProvince = true;

    /**
     * 库存总量
     */
    private Integer quantity = 0;


    public GeoStockResponseVo(String productCode, Integer geoId, Integer quantity) {
        this.productCode = productCode;
        this.geoId = geoId;
        this.quantity = quantity;
    }

    public GeoLevelEnum getGeoLevel() {
        return geoLevel;
    }

    public void setGeoLevel(GeoLevelEnum geoLevel) {
        this.geoLevel = geoLevel;
    }

    public Integer getGeoId() {
        return geoId;
    }

    public void setGeoId(Integer geoId) {
        this.geoId = geoId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getProvince() {
        return isProvince;
    }

    public void setProvince(Boolean province) {
        isProvince = province;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
