package com.biz.vo.org;


import com.biz.gbck.common.model.InitGlobalParams;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.support.web.assist.GlobalParams;

/**
 * Created by JKLiues on 2016/4/6.
 */
public class ShopUpdateDetailVo extends CommonReqVoBindUserId
        implements InitGlobalParams {

    private Integer provinceId;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 市
     */
    private Integer cityId;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区
     */
    private Integer districtId;

    /**
     * 区县名称
     */
    private String districtName;

    /**
     * 变更原因
     */
    private String reason;

    /**
     * 被拒原因
     */
    private String rejectReasons;

    private Integer auditStatus;

    /**
     * 送货地址
     */
    private String deliveryAddress;

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public String getRejectReasons() {
        return rejectReasons;
    }

    public void setRejectReasons(String rejectReasons) {
        this.rejectReasons = rejectReasons;
    }

    private GlobalParams globalParams;

    @Override
    public void setGlobalParams(GlobalParams globalParams) {
        this.globalParams = globalParams;
    }

    @Override
    public GlobalParams getGlobalParams() {
        return globalParams;
    }

}
