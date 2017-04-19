package com.biz.gbck.vo.geo;

import com.biz.gbck.enums.product.GeoLevelEnum;
import java.io.Serializable;
import java.math.BigDecimal;

public class AbstractMnsGeoVo implements Serializable {

    private static final long serialVersionUID = -1225908257699884194L;

    protected Integer id;

    protected String code;

    protected String name;

    protected String baiduname;

    protected Integer weight;

    protected BigDecimal lat;

    protected BigDecimal lon;

    protected Integer idx;

    protected String description;

    protected String coordinate;

    protected String categorybitstr;

    protected Integer status;

    protected GeoLevelEnum geoLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaiduname() {
        return baiduname;
    }

    public void setBaiduname(String baiduname) {
        this.baiduname = baiduname;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCategorybitstr() {
        return categorybitstr;
    }

    public void setCategorybitstr(String categorybitstr) {
        this.categorybitstr = categorybitstr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
