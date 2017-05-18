package com.biz.gbck.vo.org;


import com.biz.gbck.common.model.InitGlobalParams;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.support.web.assist.GlobalParams;

import javax.validation.constraints.NotNull;

/**
 * Created by defei on 3/16/16.
 */
public class ShopChangeDeliveryAddressReqVo extends CommonReqVoBindUserId
    implements InitGlobalParams {

    /**
     * 店铺id
     */
    @NotNull private String shopId;

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
     * 送货地址
     */
    @NotNull(message = "送货地址不能为空") private String deliveryAddress;

    /**
     * 变更原因
     */
    @NotNull(message = "变更原因不能为空") private String reason;

    private GlobalParams globalParams;

    @Override public void setGlobalParams(GlobalParams globalParams) {
        this.globalParams = globalParams;
    }

    @Override public GlobalParams getGlobalParams() {
        return globalParams;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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
