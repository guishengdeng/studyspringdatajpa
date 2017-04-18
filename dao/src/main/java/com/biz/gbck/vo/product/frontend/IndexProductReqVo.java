package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.vo.product.frontend.interfaces.AbstractProductReqVo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 首页商品请求Vo
 *
 * @author david-liu
 * @date 2017年03月30日
 * @reviewer
 */
public class IndexProductReqVo extends AbstractProductReqVo implements Serializable {
    private static final long serialVersionUID = -4471528215926751037L;

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

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }

    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "IndexProductReqVo{" +
                "geoId=" + geoId +
                ", depotCode='" + depotCode + '\'' +
                ", warehouseDepotCode='" + warehouseDepotCode + '\'' +
                ", userLevel=" + userLevel +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", productCodes=" + productCodes +
                '}';
    }
}
