package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.vo.product.frontend.interfaces.AbstractProductReqVo;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 首页广告位商品数据请求 vo
 *
 * @author yanweijin
 * @date 2017/2/25
 */
public class IndexAdProductReqVo extends AbstractProductReqVo {
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


    /**
     * 商品编码
     */
    private List<String> productCodes = Collections.emptyList();


    @Override
    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    @Override
    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    @Override
    public String getWarehouseDepotCode() {
        return warehouseDepotCode;
    }

    public void setWarehouseDepotCode(String warehouseDepotCode) {
        this.warehouseDepotCode = warehouseDepotCode;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
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

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }
}
