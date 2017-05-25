package com.biz.gbck.vo.org;


import com.biz.gbck.common.vo.CommonReqVoBindUserId;

import javax.validation.constraints.NotNull;

/**
 * Created by defei on 3/16/16.
 */
public class ShopUpdateDetailReqVo extends CommonReqVoBindUserId {


    /**
     * 店铺id
     */
    @NotNull private String shopId;

    /**
     * 店铺名称
     */
    @NotNull(message = "商户名称不能为空") private String name;

    /**
     * 法人名字
     */
    @NotNull(message = "法人名字不能为空")
    private String corporateName;

    /**
     * 店铺类型
     */
    @NotNull(message = "商户类型不能为空") private Long shopTypeId;

    /**
     * 省
     */
    @NotNull private Integer provinceId;

    /**
     * 市
     */
    @NotNull private Integer cityId;

    /**
     * 区
     */
    @NotNull private Integer districtId;

    /**
     * 详细地址
     */
    @NotNull private String deliveryAddress;

    /**
     * 修改原因
     */
    private String reason;

    @Override public String toString() {
        return "ShopUpdateDetailReqVo{" +
            "userId=" + userId +
            ", shopId=" + shopId +
            ", name='" + name + '\'' +
            ", corporateName='" + corporateName + '\'' +
            ", shopTypeId=" + shopTypeId +
            ", provinceId=" + provinceId +
            ", cityId=" + cityId +
            ", districtId=" + districtId +
            ", deliveryAddress='" + deliveryAddress + '\'' +
            ", reason='" + reason + '\'' +
            '}';
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
