package com.biz.gbck.vo.org;


import com.biz.gbck.enums.user.AuditStatus;

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺审核处理vo
 * Created by defei on 4/1/16.
 */
public class ShopAuditReqVo {

    private String inviterCode;
    /**
     * 店铺经度
     */
    private BigDecimal longitude;

    /**
     * 店铺纬度
     */
     private BigDecimal latitude;


    /**
     * 智选价格标签
     */
    private List<Integer> priceTagIds;

    /**
     * 智选分类标签
     */
    private List<Integer> businessTagIds;

    /**
     * 店铺详情id
     */
    private Long shopDetailId;

    /**
     * 收货地址
     */
    private Integer provinceId;  //省
    private Integer cityId;     //市
    private Integer districtId;  //县
    private String deliveryAddress;  //详细地址

    /**
     * 价格门店
     */
    private String depotId;

    /**
     * 配送门店
     */
    private String deliveryDepotId;

    /**
     * 配送门店
     */
    private String supportPaymentIds;
    /**
     * 开荒门店
     */
    private String assartDepotId;

    /**
     * 商户资质id
     */
    private Long shopQualificationId;

    /**
     * 审核结果
     */
    private AuditStatus auditStatus;

    /**
     * 营业执照ID
     */
    private String businessLicenceId;

    /**
     * 营业执照名称
     */
    private String businessLicenceName;


    /**
     * 酒类流通许可证ID
     */
    private String liquorSellLicenceId;

    /**
     * 法人身份证Id
     */
    private String corporateId;

    /**
     * 资质被拒原因
     */
    private List<String> auditRejectReasons;

    /**
     * 处理者
     */
    private String handler;
    /**
     * 商品类型
     */
    private Long shopTypeId;
    /**
     * 商户id
     */
    private Long shopId;

    /**
     * 商户合伙人id
     */
    private Long partnerId;

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(Long shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public Long getShopDetailId() {
        return shopDetailId;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public void setShopDetailId(Long shopDetailId) {
        this.shopDetailId = shopDetailId;
    }

    public Long getShopQualificationId() {
        return shopQualificationId;
    }

    public void setShopQualificationId(Long shopQualificationId) {
        this.shopQualificationId = shopQualificationId;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getBusinessLicenceId() {
        return businessLicenceId;
    }

    public void setBusinessLicenceId(String businessLicenceId) {
        this.businessLicenceId = businessLicenceId;
    }

    public String getBusinessLicenceName() {
        return businessLicenceName;
    }

    public void setBusinessLicenceName(String businessLicenceName) {
        this.businessLicenceName = businessLicenceName;
    }

    public String getLiquorSellLicenceId() {
        return liquorSellLicenceId;
    }

    public void setLiquorSellLicenceId(String liquorSellLicenceId) {
        this.liquorSellLicenceId = liquorSellLicenceId;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public List<String> getAuditRejectReasons() {
        return auditRejectReasons;
    }

    public void setAuditRejectReasons(List<String> auditRejectReasons) {
        this.auditRejectReasons = auditRejectReasons;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getDeliveryDepotId() {
        return deliveryDepotId;
    }

    public void setDeliveryDepotId(String deliveryDepotId) {
        this.deliveryDepotId = deliveryDepotId;
    }

    public String getSupportPaymentIds() {
        return supportPaymentIds;
    }

    public void setSupportPaymentIds(String supportPaymentIds) {
        this.supportPaymentIds = supportPaymentIds;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getAssartDepotId() {
        return assartDepotId;
    }

    public void setAssartDepotId(String assartDepotId) {
        this.assartDepotId = assartDepotId;
    }

    public List<Integer> getPriceTagIds() {
        return priceTagIds;
    }

    public void setPriceTagIds(List<Integer> priceTagIds) {
        this.priceTagIds = priceTagIds;
    }

    public List<Integer> getBusinessTagIds() {
        return businessTagIds;
    }

    public void setBusinessTagIds(List<Integer> businessTagIds) {
        this.businessTagIds = businessTagIds;
    }

    public String getInviterCode() {
        return inviterCode;
    }

    public void setInviterCode(String inviterCode) {
        this.inviterCode = inviterCode;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }
}
