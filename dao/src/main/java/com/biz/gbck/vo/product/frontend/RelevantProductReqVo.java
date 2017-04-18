package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.vo.product.frontend.interfaces.AbstractProductReqVo;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

/**
 * 关联商品请求Vo
 *
 * @author david-liu
 * @date 2017年02月16日
 * @reviewer
 */
public class RelevantProductReqVo extends AbstractProductReqVo {
    private static final long serialVersionUID = 7849915961260147218L;

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
     * 定位经度
     */
    private BigDecimal latitude;

    /**
     * 定位纬度
     */
    private BigDecimal longitude;

    /**
     * 用户等级
     */
    private Integer userLevel = 1;

    /**
     * 商品编码
     */
    private String productCode;

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

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public boolean isValid() {
        boolean isProductCodeValid = StringUtils.isNotBlank(this.productCode);
        return super.isValid() && isProductCodeValid;
    }

    @Override
    public String getInvalidMessage() {
        boolean isProductCodeValid = StringUtils.isNotBlank(this.productCode);
        if (!isProductCodeValid) {
            return "关联商品源商品编码为空";
        } else {
            return super.getInvalidMessage();
        }
    }
}
