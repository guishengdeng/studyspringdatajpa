package com.biz.gbck.vo.org;


import com.biz.core.util.DateUtil;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 店铺 用于manage商户集合 dylan 17-5-17
 */
public class ShopDetailResVo implements Serializable {

    private Long shopDetailId;  //详情id

    private Long shopQualificationId;  //资质id

    private Long shopId; //商户id

    private String name;

    private String corporateName; //法人名称

    private Long shopTypeId; //店铺类型

    private String shopTypeName;

    private String mobile;

    private String deliveryName; //收货人姓名

    private String deliveryMobile; //收货人电话

    private String deliveryAddress; //店铺收货地址

    private String shopAddress; //店铺地址

    private Integer provinceId; //省

    private String provinceName;

    private Integer cityId; //市

    private String cityName;

    private Integer districtId; //区

    private String districtName;

    private Timestamp createTime = DateUtil.now(); //创建时间

    private Timestamp handTime; //审核时间

    private String handlerUserName; //处理人

    private String rejectReason; //被拒原因

    private String reason; //申请修改的原因

    private Integer auditStatus; //审核状态

     private String businessLicenceId; //营业执照ID

    private String businessLicenceName; //营业执照名称

    private String businessLicence; //营业执照

     private String shopPhoto; //门头照片

     private String liquorSellLicenceId; //酒类流通许可证ID

     private String liquorSellLicence; //酒类流通许可证

    private String corporateId; //法人身份证Id

    private String corporateIdPhoto; //法人身份证

    private String partnerName; //城市合伙人名称

    private String shopStatus; //商户状态

    public Long getShopDetailId() {
        return shopDetailId;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public Long getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(Long shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public String getShopTypeName() {
        return shopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryMobile() {
        return deliveryMobile;
    }

    public void setDeliveryMobile(String deliveryMobile) {
        this.deliveryMobile = deliveryMobile;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getHandTime() {
        return handTime;
    }

    public void setHandTime(Timestamp handTime) {
        this.handTime = handTime;
    }

    public String getHandlerUserName() {
        return handlerUserName;
    }

    public void setHandlerUserName(String handlerUserName) {
        this.handlerUserName = handlerUserName;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
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

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getShopPhoto() {
        return shopPhoto;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    public String getLiquorSellLicenceId() {
        return liquorSellLicenceId;
    }

    public void setLiquorSellLicenceId(String liquorSellLicenceId) {
        this.liquorSellLicenceId = liquorSellLicenceId;
    }

    public String getLiquorSellLicence() {
        return liquorSellLicence;
    }

    public void setLiquorSellLicence(String liquorSellLicence) {
        this.liquorSellLicence = liquorSellLicence;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public String getCorporateIdPhoto() {
        return corporateIdPhoto;
    }

    public void setCorporateIdPhoto(String corporateIdPhoto) {
        this.corporateIdPhoto = corporateIdPhoto;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
