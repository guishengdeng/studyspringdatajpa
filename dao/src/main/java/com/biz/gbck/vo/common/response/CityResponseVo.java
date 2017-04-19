package com.biz.gbck.vo.common.response;

import com.biz.gbck.vo.common.AbstractAreaResponseVo;

/**
 * 城市信息vo
 * Created by maoyikun on 16-12-16.
 */
public class CityResponseVo extends AbstractAreaResponseVo {

    /**
     * 省id
     */
    private Integer provinceId;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}
