package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品详情请求Vo
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public class ProductDetailRequestVo implements Serializable {
    private static final long serialVersionUID = -6090912565913699081L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 门店编码
     * <pre>
     *    1. 如果是开启了快喝模式有定位到附近门店, 该参数为距离定位最近的门店的门店编码
     *    2. 如果没开启快喝模式或者定位附近没有门店, 该参数不传
     * </pre>
     */
    private String depotCode;

    /**
     * 省仓门店编码
     * <pre>
     *     1. 查询A类商品的详情, 该参数不传
     *     2. 查询B类商品的详情, 该参数必传
     * </pre>
     */
    private String warehouseDepotCode;

    /**
     * 商家Id(预留字段, 考虑兼容之后可能出现一品多商的需求)
     */
    private Long vendorId;

    /**
     * 用户等级
     */
    private Integer userLevel = 1;

    /**
     * 定位经度
     */
    private BigDecimal latitude;

    /**
     * 定位纬度
     */
    private BigDecimal longitude;
    
    /**
     * 是否通过库存校验
     */
    private Boolean validateStock = true;

    /**
     * 销售区域编码
     * 为空时 不校验区域
     */
    private String geoId;

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
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

	public Boolean getValidateStock() {
        return validateStock;
    }

    public void setValidateStock(Boolean validateStock) {
        this.validateStock = validateStock;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
