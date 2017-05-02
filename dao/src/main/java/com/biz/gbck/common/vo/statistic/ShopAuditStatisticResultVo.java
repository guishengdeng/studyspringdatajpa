package com.biz.gbck.common.vo.statistic;

public class ShopAuditStatisticResultVo {
    private Number provinceId;

    private String provinceName;

    private String depotName;

    private Number registerNotCompleteShopCount;

    private Number auditNotPassShopCount;

    private Number auditPassShopCount;

    public ShopAuditStatisticResultVo() {
    }

    public ShopAuditStatisticResultVo(Number provinceId, String provinceName, String depotName,
                                      Number registerNotCompleteShopCount, Number auditNotPassShopCount,
                                      Number auditPassShopCount) {
        this.auditNotPassShopCount = auditNotPassShopCount;
        this.auditPassShopCount = auditPassShopCount;
        this.depotName = depotName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.registerNotCompleteShopCount = registerNotCompleteShopCount;
    }

    public Number getAuditNotPassShopCount() {
        return auditNotPassShopCount;
    }

    public void setAuditNotPassShopCount(Number auditNotPassShopCount) {
        this.auditNotPassShopCount = auditNotPassShopCount;
    }

    public Number getAuditPassShopCount() {
        return auditPassShopCount;
    }

    public void setAuditPassShopCount(Number auditPassShopCount) {
        this.auditPassShopCount = auditPassShopCount;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public Number getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Number provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Number getRegisterNotCompleteShopCount() {
        return registerNotCompleteShopCount;
    }

    public void setRegisterNotCompleteShopCount(Number registerNotCompleteShopCount) {
        this.registerNotCompleteShopCount = registerNotCompleteShopCount;
    }
}
