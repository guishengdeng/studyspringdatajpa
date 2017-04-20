package com.biz.gbck.dao.mysql.po.user;


import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 店铺
 * Created by defei on 3/16/16.
 */
@Entity
@Table(name = "shop_detail")
public class ShopDetailPo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    private ShopPo shop;

    /**
     * 店铺名称
     */
    @Column(length = 40)
    private String name;

    /**
     * 法人名称
     */
    private String corporateName;

    /**
     * 店铺类型
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopTypeId")
    private ShopTypePo shopType;

    /**
     * 店铺手机
     */
    @Column(nullable = false, length = 11)
    private String mobile;

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
    @Column(length = 100)
    private String deliveryAddress;

    /**
     * 店铺地址
     */
    @Column(length = 100)
    private String shopAddress;

    /**
     * 省
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceId")
    private ProvincePo province;

    /**
     * 市
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId")
    private CityPo city;

    /**
     * 区
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtId")
    private DistrictPo district;

    /**
     * 创建时间
     */
    private Timestamp createTime = DateUtil.now();

    /**
     * 审核时间
     */
    private Timestamp handTime;

    @Column(length = 50)
    private String handlerUserName;

    /**
     * 被拒原因
     */
    private String rejectReason;
    /**
     * 申请修改的原因
     */
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 审核状态
     */
    private Integer auditStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShopPo getShop() {
        return shop;
    }

    public void setShop(ShopPo shop) {
        this.shop = shop;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShopTypePo getShopType() {
        return shopType;
    }

    public void setShopType(ShopTypePo shopType) {
        this.shopType = shopType;
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

    public ProvincePo getProvince() {
        return province;
    }

    public void setProvince(ProvincePo province) {
        this.province = province;
    }

    public CityPo getCity() {
        return city;
    }

    public void setCity(CityPo city) {
        this.city = city;
    }

    public DistrictPo getDistrict() {
        return district;
    }

    public void setDistrict(DistrictPo district) {
        this.district = district;
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

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
