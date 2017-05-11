package com.biz.gbck.vo.voucher;

import java.util.Date;

public class UserVoucherStatisticResultVo {

    private Date time;

    private String userMobile;

    private Number userId;

    private String provinceName;

    private String shopTypeName;

    private String voucherTypeName;

    private Number voucherTypeId;

    private Number voucherIssueCount;

    private Number userVoucherCount;

    private Number userVoucherRemianCount;

    public UserVoucherStatisticResultVo() {

    }

    public UserVoucherStatisticResultVo(Date time, String userMobile, Number userId, String provinceName,
        String shopTypeName, String voucherTypeName, Number voucherTypeId,
        Number voucherIssueCount) {
        this.time = time;
        this.userMobile = userMobile;
        this.userId = userId;
        this.provinceName = provinceName;
        this.shopTypeName = shopTypeName;
        this.voucherTypeId = voucherTypeId;
        this.voucherIssueCount = voucherIssueCount;
        this.voucherTypeName = voucherTypeName;
    }

    public Number getUserVoucherCount() {
        return userVoucherCount;
    }

    public void setUserVoucherCount(Number userVoucherCount) {
        this.userVoucherCount = userVoucherCount;
    }

    public Number getUserVoucherRemianCount() {
        return userVoucherRemianCount;
    }

    public void setUserVoucherRemianCount(Number userVoucherRemianCount) {
        this.userVoucherRemianCount = userVoucherRemianCount;
    }

    public Number getVoucherTypeId() {
        return voucherTypeId;
    }

    public void setVoucherTypeId(Number voucherTypeId) {
        this.voucherTypeId = voucherTypeId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getShopTypeName() {
        return shopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Number getVoucherIssueCount() {
        return voucherIssueCount;
    }

    public void setVoucherIssueCount(Number voucherIssueCount) {
        this.voucherIssueCount = voucherIssueCount;
    }

    public String getVoucherTypeName() {
        return voucherTypeName;
    }

    public void setVoucherTypeName(String voucherTypeName) {
        this.voucherTypeName = voucherTypeName;
    }

    public Number getUserId() {
        return userId;
    }

    public void setUserId(Number userId) {
        this.userId = userId;
    }
}
