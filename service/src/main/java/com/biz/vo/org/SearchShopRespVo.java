package com.biz.vo.org;


import com.biz.gbck.dao.mysql.po.org.ShopTypePo;

/**
 * Created by J on 2016/3/26.
 */
public class SearchShopRespVo implements java.io.Serializable {
    private Long id;
    /**
     * 店铺名称
     */
    private String name;
    /**
     * 店铺类型
     */
    private ShopTypePo shopType;
    /**
     * 店铺手机
     */
    private String mobile;
    /**
     * 店铺地址
     */
    private String shopAddress;

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * 收货地址
     */
    private String deliveryAddress;
    /**
     * 店铺状态
     */
    private Integer status;
    /**
     * 店铺审核状态
     */
    private Integer auditStatus;
    /**
     * 用户名字
     */
    private String userName;
    /**
     * 用户Id
     */
    private Long userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
