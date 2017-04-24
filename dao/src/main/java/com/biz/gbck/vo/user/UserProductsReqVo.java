package com.biz.gbck.vo.user;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户足迹商品请求Vo
 *
 * @author david-liu
 * @date 2017年01月16日
 * @reviewer
 */
public class UserProductsReqVo implements Serializable {
    private static final long serialVersionUID = 6513285742264906802L;

    /**
     * 用户日期商品足迹
     */
    private List<String> productCodes;

    /**
     * 用户等级
     */
    private Integer userLevel = 1;

    /**
     * 店编码(未开启快喝模式可以不传)
     */
    private String depotCode;

    /**
     * 省仓门店编码(必传)
     */
    private String warehouseDepotCode;

    /**
     * GeoId
     */
    private Long geoId;

    //当前纬度
    private BigDecimal lat;

    //当前经度
    private BigDecimal lon;

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

    public List<String> getProductCodes() {
        if (CollectionUtils.isNotEmpty(this.productCodes)) {
            return productCodes;
        } else {
            return Lists.newArrayList();
        }
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
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

    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
