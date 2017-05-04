package com.biz.gbck.common.vo.statistic;

public class DailyStatNewShopResultVo {
    private String dDay;

    private Number provinceId;

    private Number shopTypeId;

    private Number newShopCount;

    public DailyStatNewShopResultVo() {
    }

    public DailyStatNewShopResultVo(String dDay, Number provinceId,
                                    Number shopTypeId, Number newShopCount) {
        this.dDay = dDay;
        this.newShopCount = newShopCount;
        this.provinceId = provinceId;
        this.shopTypeId = shopTypeId;
    }

    public String getdDay() {
        return dDay;
    }

    public void setdDay(String dDay) {
        this.dDay = dDay;
    }

    public Number getNewShopCount() {
        return newShopCount;
    }

    public void setNewShopCount(Number newShopCount) {
        this.newShopCount = newShopCount;
    }

    public Number getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Number provinceId) {
        this.provinceId = provinceId;
    }

    public Number getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(Number shopTypeId) {
        this.shopTypeId = shopTypeId;
    }
}
