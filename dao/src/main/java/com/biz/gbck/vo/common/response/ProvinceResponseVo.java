package com.biz.gbck.vo.common.response;

import com.biz.gbck.vo.common.AbstractAreaResponseVo;

/**
 * 省份信息vo
 *
 * @author yanweijin
 * @date 2016/12/16
 */
public class ProvinceResponseVo extends AbstractAreaResponseVo {
    /**
     * 区域id
     */
    private Integer regionId;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
