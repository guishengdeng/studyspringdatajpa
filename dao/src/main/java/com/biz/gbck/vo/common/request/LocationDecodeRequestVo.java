package com.biz.gbck.vo.common.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 定位解码ReqVo
 *
 * @author david-liu
 * @date 2016年09月12日
 * @reviewer
 * @see
 */
public class LocationDecodeRequestVo implements Serializable {

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String cityName;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
