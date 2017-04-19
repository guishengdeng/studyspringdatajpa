package com.biz.gbck.vo.geo;


public class MnsGeoDistrictVo extends AbstractMnsGeoVo {

    private static final long serialVersionUID = 106786233700869848L;

    private Integer cityId;

    private Integer provinceid;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }
}
