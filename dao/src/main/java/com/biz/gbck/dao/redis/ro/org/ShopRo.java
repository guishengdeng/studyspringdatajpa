package com.biz.gbck.dao.redis.ro.org;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 店铺
 */
@Ro(key = "org:shopRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class ShopRo extends BaseRedisObject<String> implements Serializable {



    /**
     * 店铺名称
     */
    private String name;

    /**
     * 法人名字
     */
    private String corporateName;

    /**
     * 店铺头像
     */
    private String avatar;

    /**
     * 店铺成员
     */
    private String users;

    /**
     * 店铺类型
     */
    private Long shopTypeId;

    /**
     * 店铺手机
     */
    private String mobile;

    /**
     * 店铺电话
     */
    private String tel;

    /**
     * 绑定门店价格门店id
     */
    private String depotId;

    
    /**
     * 配送门店Id
     */
    private String deliveryDepotId;
    
    
    /**
     * 店铺经度
     */
    private BigDecimal longitude;

    /**
     * 店铺纬度
     */
    private BigDecimal latitude;

    /**
     * 收货人姓名
     */
    private String deliveryName;

    /**
     * 收货人电话
     */
    private String deliveryMobile;

    /**
     * 店铺收货地址
     */
    private String deliveryAddress;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 省
     */
    private Long provinceId;

    /**
     * 市
     */
    private Long cityId;

    /**
     * 区
     */
    private Long districtId;

    /**
     * 营业执照ID
     */
    private String businessLicenceId;

    /**
     * 营业执照名称
     */
    private String businessLicenceName;

    /**
     * 营业执照
     */
    private String businessLicence;

    /**
     * 门头照片
     */
    private String shopPhoto;

    /**
     * 酒类流通许可证ID
     */
    private String liquorSellLicenceId;

    /**
     * 酒类流通许可证
     */
    private String liquorSellLicence;

    /**
     * 法人身份证Id
     */
    private String corporateId;

    /**
     * 法人身份证
     */
    private String corporateIdPhoto;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 智选价格标签
     */
    private String priceTags;

    /**
     * 智选分类标签
     */
    private String businessTags;

    /**
     * 销售区域
     */
    private String saleAreas;

    /**
     * 邀请者
     */
    private String inviterCode;

    /**
     * 商户详情审核状态
     */
    private Integer detailAuditStatus;

    /**
     * 资质审核状态
     */
    private Integer qualificationAuditStatus;

    /**
     * 店铺状态
     */
    private Integer status;

    /**
     * 支持的 付款方式
     */
    private String supportPaymentIds;

    /**
     * 禁用的付款方式
     */
    private String disabledPaymentIds;

    /**
     * 支付密码
     */
    private String paymentPassword;


    private Long parentId;

    private Integer channel;

    private Long channelUserId;


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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public Long getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(Long shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * {@linkplain ShopRo#depotId}
     */
    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

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

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getPriceTags() {
        return priceTags;
    }

    public void setPriceTags(String priceTags) {
        this.priceTags = priceTags;
    }

    public String getBusinessTags() {
        return businessTags;
    }

    public void setBusinessTags(String businessTags) {
        this.businessTags = businessTags;
    }

    public String getSaleAreas() {
        return saleAreas;
    }

    public void setSaleAreas(String saleAreas) {
        this.saleAreas = saleAreas;
    }

    public String getInviterCode() {
        return inviterCode;
    }

    public void setInviterCode(String inviterCode) {
        this.inviterCode = inviterCode;
    }

    public Integer getDetailAuditStatus() {
        return detailAuditStatus;
    }

    public void setDetailAuditStatus(Integer detailAuditStatus) {
        this.detailAuditStatus = detailAuditStatus;
    }

    public Integer getQualificationAuditStatus() {
        return qualificationAuditStatus;
    }

    public void setQualificationAuditStatus(Integer qualificationAuditStatus) {
        this.qualificationAuditStatus = qualificationAuditStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getDisabledPaymentIds() {
        return disabledPaymentIds;
    }

    public void setDisabledPaymentIds(String disabledPaymentIds) {
        this.disabledPaymentIds = disabledPaymentIds;
    }

    public Long getParentId() {
        return parentId;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Long getChannelUserId() {
        return channelUserId;
    }

    public void setChannelUserId(Long channelUserId) {
        this.channelUserId = channelUserId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String toString(){
    	return ToStringBuilder.reflectionToString(this);
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

}
