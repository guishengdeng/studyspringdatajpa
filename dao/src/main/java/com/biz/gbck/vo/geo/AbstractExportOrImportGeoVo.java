package com.biz.gbck.vo.geo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 导出Geo信息的基类
 *
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public class AbstractExportOrImportGeoVo implements Serializable {

    private Integer id;

    protected String code;

    protected String name;

    protected Integer idx;

    protected Integer weight = 0;

    protected String coordinate;

    protected BigDecimal lat;

    protected BigDecimal lon;

    protected String baiduname;

    protected String description;

    protected Integer baiducode;

    protected String post;

    protected String prefix;

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

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
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

    public String getBaiduname() {
        return baiduname;
    }

    public void setBaiduname(String baiduname) {
        this.baiduname = baiduname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBaiducode() {
        return baiducode;
    }

    public void setBaiducode(Integer baiducode) {
        this.baiducode = baiducode;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
