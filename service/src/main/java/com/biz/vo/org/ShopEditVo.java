package com.biz.vo.org;

import com.biz.gbck.dao.mysql.po.org.ShopLevel;
import org.codelogger.utils.ArrayUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by defei on 4/3/16.
 */
public class ShopEditVo {

    /**
     * 店铺id
     */
    private Long shopId;

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
     * 价格门店
     */
    private String depotId;

    /**
     * 配送门店
     */
    private String deliveryDepotId;
    /**
     * 开发门店
     */
    private String assartDepotId;

    public String getAssartDepotId() {
        return assartDepotId;
    }



    /**
     * 支持付款类型
     */
    private String supportPaymentIds;

    /**
     * 禁用的付款方式
     */
    private String disabledPaymentIds;

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
    private Integer provinceId;

    /**
     * 市
     */
    private Integer cityId;

    /**
     * 区
     */
    private Integer districtId;

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
    private List<Integer> priceTagIds;

    /**
     * 智选分类标签
     */
    private List<Integer> businessTagIds;

    /**
     * 销售区域
     */
    private List<Integer> saleAreaIds;

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
     * 会员等级
     */
    private ShopLevel shopLevel;

    /**
     * 店铺状态
     */
    private Integer status;

    public String getDisabledPaymentIds() {
        return disabledPaymentIds;
    }

    public void setDisabledPaymentIds(String disabledPaymentIds) {
        this.disabledPaymentIds = disabledPaymentIds;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
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

    public void setSupportPaymentIds(String[] supportPaymentIds) {
        this.supportPaymentIds = ArrayUtils.join(supportPaymentIds, ",");
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

    public void setAssartDepotId(String assartDepotId) {
        this.assartDepotId = assartDepotId;
    }

    public void setSupportPaymentIds(String supportPaymentIds) {
        this.supportPaymentIds = supportPaymentIds;
    }

    public String getCorporateIdPhoto() {
        return corporateIdPhoto;
    }

    public void setCorporateIdPhoto(String corporateIdPhoto) {
        this.corporateIdPhoto = corporateIdPhoto;
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


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public List<Integer> getSaleAreaIds() {
        return saleAreaIds;
    }

    public void setSaleAreaIds(List<Integer> saleAreaIds) {
        this.saleAreaIds = saleAreaIds;
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

    public ShopLevel getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(ShopLevel shopLevel) {
        this.shopLevel = shopLevel;
    }
}
