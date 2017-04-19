package com.biz.gbck.vo.common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 省市区信息基类
 * Created by maoyikun on 16-12-16.
 */
public abstract class AbstractAreaResponseVo implements Serializable {

    /**
     * 省市区对应的id
     */
    protected Integer id;

    /**
     * 省市区名称对应的拼音首字母简写
     */
    protected String code;

    /**
     * 省市区显示的名称
     */
    protected String name;

    /**
     * 显示顺序
     */
    protected Integer idx;

    /**
     * 权重
     */
    protected Integer weight = 0;

    /**
     * 省市区对应的边界
     */
    protected String coordinate;

    /**
     * 纬度
     */
    protected BigDecimal lat;

    /**
     * 经度
     */
    protected BigDecimal lon;

    /**
     * 百度地理名称
     */
    protected String baiduname;

    /**
     * 描述
     */
    protected String description;

    /**
     * 百度code
     */
    protected Integer baiducode;

    /**
     * 邮政编码
     */
    protected String post;

    /**
     * 名称对应的拼音首字母
     */
    protected String prefix;

    protected Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
