package com.biz.rest.vo.org;


import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.enums.user.AuditRejectReason;

import java.util.List;


public class SimpleShopDetail {

    public Long shopId;

    public String corporateName;

    public String name;

    public Long shopTypeId;

    public String shopTypeName;

    public String deliveryAddress;

    public Integer provinceId;

    public String provinceName;

    public Integer cityId;

    public String cityName;

    public Integer districtId;

    public String districtName;

    public List<String> rejectReasons;

    public Integer auditStatus;

    public SimpleShopDetail(ShopDetailPo po) {

        if (po != null) {
            shopId = po.getShop().getId();
            name = po.getName();
            corporateName = po.getCorporateName();
            shopTypeId = po.getShopType() == null ? null : po.getShopType().getId();
            shopTypeName = po.getShopType() == null ? null : po.getShopType().getName();
            deliveryAddress = po.getDeliveryAddress();
            provinceId = po.getProvince() == null ? null : po.getProvince().getId();
            provinceName = po.getProvince() == null ? null : po.getProvince().getName();
            cityId = po.getCity() == null ? null : po.getCity().getId();
            cityName = po.getCity() == null ? null : po.getCity().getName();
            districtId = po.getDistrict() == null ? null : po.getDistrict().getId();
            districtName = po.getDistrict() == null ? null : po.getDistrict().getName();
            String rejectReason = po.getRejectReason();
            rejectReasons = rejectReason == null ?
                null :
                AuditRejectReason.parseRejectReasons(rejectReason.split("\\D"));
            auditStatus = po.getAuditStatus();
        }
    }
}
