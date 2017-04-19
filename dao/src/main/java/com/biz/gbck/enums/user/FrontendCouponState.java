package com.biz.gbck.enums.user;

/**
 * Created by JKLiues on 2017/2/18.
 */
public enum FrontendCouponState {
    ALL("全部"),
    CAN_USE("可用"),
    USED("已用"),
    UNENFORCED_OR_EXPIRE("未生效或已过期");

    private String desc;

    FrontendCouponState(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
