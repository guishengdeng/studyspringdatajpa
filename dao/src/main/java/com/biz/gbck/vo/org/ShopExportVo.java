package com.biz.gbck.vo.org;

import java.sql.Timestamp;

/**
 * Created by JKLiues on 2016/5/19.
 */
public class ShopExportVo {
    //    商户手机 商户名称  创建时间 审核状态  推荐人工号  推荐人姓名  所在省份
    private String mobile;
    private String shopName;
    private Timestamp createTime;
    private Timestamp latestLoginTime;
    private String auditStaus;
    private String shopType;
    private String invaterCode;
    private String invaterName;
    private String ProvinceName;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(Timestamp latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }

    public String getAuditStaus() {
        return auditStaus;
    }

    public void setAuditStaus(String auditStaus) {
        this.auditStaus = auditStaus;
    }

    public String getInvaterCode() {
        return invaterCode;
    }

    public void setInvaterCode(String invaterCode) {
        this.invaterCode = invaterCode;
    }

    public String getInvaterName() {
        return invaterName;
    }

    public void setInvaterName(String invaterName) {
        this.invaterName = invaterName;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }
}
