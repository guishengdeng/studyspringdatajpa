package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.vo.product.frontend.interfaces.AbstractProductReqVo;
import java.math.BigDecimal;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/28
 */
public class ListTopNReqVo extends AbstractProductReqVo {
    /**
     * 分类ID
     */
    Long categoryId;
    /**
     * 获得商品个数
     */
    Integer size;
    /**
     * 区域ID
     */
    private Long geoId;
    /**
     * 门店编码
     */
    private String depotCode;
    /**
     * 省仓门店编码
     */
    private String warehouseDepotCode;
    /**
     * 用户等级
     */
    private Integer userLevel = 1;
    /**
     * 经度
     */
    private BigDecimal latitude;
    /**
     * 纬度
     */
    private BigDecimal longitude;

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String getWarehouseDepotCode() {
        return warehouseDepotCode;
    }

    public void setWarehouseDepotCode(String warehouseDepotCode) {
        this.warehouseDepotCode = warehouseDepotCode;
    }

    @Override
    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    @Override
    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    @Override
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
