package com.biz.gbck.enums.user;


import java.util.Objects;

/**
 * 店铺资质状态
 * Created by defei on 3/16/16.
 */
public enum AuditStatus {

    AUDIT_FAILED("审核未通过", 0),

    DATA_EXPIRED("审核资料已过期", 5),

    NEED_INFO("等待上传资料", 10),

    WAIT_FOR_AUDIT("等待审核", 20),

    NORMAL_AND_NEW_UPDATE_AUDIT_FAILED("原资料生效，新的详细资料审核失败", 23),

    NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT("原资料生效，并已提交新的资料，等待验证", 25),

    NORMAL("审核通过", 30);

    private final String description;

    private final Integer value;

    AuditStatus(String description, Integer value) {
        this.description = description;
        this.value = value;
    }

//    public static AuditStatus valueOf(Integer value) {
//        for (AuditStatus shopDetailAuditStatus : values()) {
//            if (Objects.equals(shopDetailAuditStatus.getValue(), value)) {
//                return shopDetailAuditStatus;
//            }
//        }
//        throw new Biz("Illegal ShopDetailAuditStatus");
//    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
