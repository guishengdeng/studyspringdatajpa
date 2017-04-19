package com.biz.gbck.vo.common.response;

import com.biz.gbck.vo.common.AbstractAreaResponseVo;

/**
 * 区县信息vo
 * Created by maoyikun on 16-12-16.
 */
public class DistrictResponseVo extends AbstractAreaResponseVo {

    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 省id
     */
    private Integer provinceId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}
