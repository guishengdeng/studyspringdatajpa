package com.biz.gbck.dao.redis.ro.org;

/**
 * @author: liubin
 * @date 4/25/17 16:33
 */
public class PartnerRo extends CompanyRo {

    private Long provinceId;

    private Long cityId;

    private Long platformId;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
}
