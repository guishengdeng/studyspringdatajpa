package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.enums.product.ProductRecommend;
import com.biz.gbck.vo.product.frontend.interfaces.AbstractProductReqVo;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 推荐商品请求Vo
 *
 * @author david-liu
 * @date 2017年02月15日
 * @reviewer
 */
public class RecommendProductReqVo extends AbstractProductReqVo {
    private static final long serialVersionUID = 7707977614423956411L;

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
     * 推荐类型
     */
    private ProductRecommend type;

    /**
     * 经度
     */
    private BigDecimal latitude;

    /**
     * 纬度
     */
    private BigDecimal longitude;

    /**
     * 中台会员ID
     */
    private String memberId;

    /**
     * 商品编码(商品底层页推荐使用)
     */
    private String productCode;

    public RecommendProductReqVo() {
    }

    public RecommendProductReqVo(Long geoId, String depotCode, String warehouseDepotCode, Integer userLevel, ProductRecommend type, BigDecimal latitude, BigDecimal longitude, String memberId, String productCode) {
        this.geoId = geoId;
        this.depotCode = depotCode;
        this.warehouseDepotCode = warehouseDepotCode;
        this.userLevel = userLevel;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.memberId = memberId;
        this.productCode = productCode;
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

    public ProductRecommend getType() {
        return type;
    }

    public void setType(ProductRecommend type) {
        this.type = type;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean isValid() {
        boolean isRecommendTypeValid = this.type != null;
        return super.isValid() && isRecommendTypeValid;
    }

    public String getInvalidMessage() {
        boolean isRecommendTypeValid = this.type != null;
        if (!isRecommendTypeValid) {
            return "必须传入推荐类型";
        } else {
            return super.getInvalidMessage();
        }
    }
}
