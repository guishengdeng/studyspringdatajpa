package com.biz.gbck.vo.common.response;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用于统一返回省市区县的信息vo
 *
 * Created by maoyikun on 16-12-16.
 */
public class AreaResponseVo implements Serializable{

    /**
     * 省市区县名称
     */
    private String name;
    /**
     * 省市区县对应的id
     */
    private Integer id;
    /**
     * 纬度
     */
    private BigDecimal lat;
    /**
     * 经度
     */
    private BigDecimal lon;
    /**
     * 首字母
     */
    private String prefix;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
