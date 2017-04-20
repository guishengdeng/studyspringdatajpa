package com.biz.gbck.enums.user;

import org.apache.commons.lang.math.NumberUtils;

import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 店铺审核被拒原因
 * Created by defei on 3/16/16.
 */
public enum AuditRejectReason {

    DETAIL_INVALID(0, "详情审核不通过"),

    BUSINESS_LICENCE_PHOTO_NOT_CLEAR(1, "营业执照不清晰"),

    SHOP_PHOTO_INVALID(2, "门头照片不符合审核要求"),

    BUSINESS_LICENCE_ID_EXIST(3, "营业执照编号已被注册"),

    BUSINESS_LICENCE_TYPE_INVALID(4, "企业类型不符合"),

    BUSINESS_LICENCE_SCOP_INVALID(5, "营业执照经营范围与实际范围不符合");

    private final Integer value;

    private final String description;


    AuditRejectReason(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

//    public static AuditRejectReason valueOf(Integer value) {
//        for (AuditRejectReason shopDetailAuditStatus : values()) {
//            if (Objects.equals(shopDetailAuditStatus.getValue(), value)) {
//                return shopDetailAuditStatus;
//            }
//        }
////        throw new CommonRuntimeException("Illegal ShopDetailAuditStatus");
//    }

//    public static List<String> parseRejectReasons(String[] values) {
//
//        List<String> rejectReasons = newArrayList();
//        if (values != null) {
//            for (String value : values) {
//                if(NumberUtils.isNumber(value)){
//                    rejectReasons.add(valueOf(Integer.valueOf(value)).getDescription());
//                }
//            }
//        }
//        return rejectReasons;
//    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
