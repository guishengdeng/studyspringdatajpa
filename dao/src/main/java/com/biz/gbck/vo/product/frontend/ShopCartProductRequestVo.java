package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * BBC 购物车商品请求 Vo
 *
 * @author lei
 * @date 2017年01月16日
 * @reviewer
 * @see
 */
public class ShopCartProductRequestVo implements Serializable {

    private static final long serialVersionUID = 2132522968239735433L;

    /**
     * 用户等级
     */
    private Integer userLevel;

    /**
     * 门店编码(可选, 开启快喝模式下必传)
     */
    private String depotCode;

    /**
     * 省仓门店编码(必选)
     */
    private String warehouseDepotCode;

    /**
     * 商品编码集合(必选, 有序) （按时间 从近到远 排列）
     */
    private List<String> productCodes = newArrayList();

    //当前纬度
    private BigDecimal lat;

    //当前经度
    private BigDecimal lon;

    /**
     * GeoID
     */
    private Long geoId;

    public ShopCartProductRequestVo() {
    }

    public ShopCartProductRequestVo(List<String> productCodes, String depotCode, String warehouseDepotCode, Integer userLevel) {
        this.productCodes = productCodes;
        this.depotCode = depotCode;
        this.warehouseDepotCode = warehouseDepotCode;
        this.userLevel = userLevel;
    }

    public ShopCartProductRequestVo(List<String> productCodes, String depotCode, String warehouseDepotCode, Integer userLevel, BigDecimal lat, BigDecimal lon, Long geoId) {
        this.productCodes = productCodes;
        this.depotCode = depotCode;
        this.warehouseDepotCode = warehouseDepotCode;
        this.userLevel = userLevel;
        this.lat = lat;
        this.lon = lon;
        this.geoId = geoId;
    }


    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopCartProductRequestVo)) return false;

        ShopCartProductRequestVo that = (ShopCartProductRequestVo) o;

        if (getUserLevel() != null ? !getUserLevel().equals(that.getUserLevel()) : that.getUserLevel() != null)
            return false;
        if (getDepotCode() != null ? !getDepotCode().equals(that.getDepotCode()) : that.getDepotCode() != null)
            return false;
        if (getWarehouseDepotCode() != null ? !getWarehouseDepotCode().equals(that.getWarehouseDepotCode()) : that.getWarehouseDepotCode() != null)
            return false;
        if (getProductCodes() != null ? !getProductCodes().equals(that.getProductCodes()) : that.getProductCodes() != null)
            return false;
        if (getLat() != null ? !getLat().equals(that.getLat()) : that.getLat() != null)
            return false;
        if (getLon() != null ? !getLon().equals(that.getLon()) : that.getLon() != null)
            return false;
        return getGeoId() != null ? getGeoId().equals(that.getGeoId()) : that.getGeoId() == null;
    }

    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    @Override
    public String toString() {
        return "ShopCartProductRequestVo{" +
                "userLevel=" + userLevel +
                ", depotCode='" + depotCode + '\'' +
                ", warehouseDepotCode='" + warehouseDepotCode + '\'' +
                ", productCodes=" + productCodes +
                ", lat=" + lat +
                ", lon=" + lon +
                ", geoId=" + geoId +
                '}';
    }

    @Override
    public int hashCode() {
        int result = getUserLevel() != null ? getUserLevel().hashCode() : 0;
        result = 31 * result + (getDepotCode() != null ? getDepotCode().hashCode() : 0);
        result = 31 * result + (getWarehouseDepotCode() != null ? getWarehouseDepotCode().hashCode() : 0);
        result = 31 * result + (getProductCodes() != null ? getProductCodes().hashCode() : 0);
        result = 31 * result + (getLat() != null ? getLat().hashCode() : 0);
        result = 31 * result + (getLon() != null ? getLon().hashCode() : 0);
        result = 31 * result + (getGeoId() != null ? getGeoId().hashCode() : 0);
        return result;
    }
}
