package com.biz.gbck.vo.common.response;

import java.io.Serializable;

/**
 * @author david-liu
 * @date 2016年09月12日
 * @reviewer
 * @see
 */
public class CityItemResponseVo implements Serializable {

    private String prefix;

    private Integer cityId;

    private String cityName;

    private String provinceName;

    private Integer provinceId;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return "CityItemResponseVo{" +
                "prefix='" + prefix + '\'' +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", provinceId=" + provinceId +
                '}';
    }
}
