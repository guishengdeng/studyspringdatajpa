package com.biz.gbck.dao.mysql.po.geo;

import com.biz.gbck.common.model.IWeight;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@SuppressWarnings("serial") @MappedSuperclass public class AbstractAreaWithCode {

    @Id private Integer id;

    @JsonIgnore
    @Column(length = 50, nullable = false) protected String code;

    @Column(length = 50) protected String name;

    @JsonIgnore
    @Column protected Integer idx;

    @JsonIgnore
    @Column protected Integer weight = IWeight.NORMARL;

    @JsonIgnore
    @Column(columnDefinition = "MEDIUMTEXT") protected String coordinate;

    @JsonIgnore
    @Column(columnDefinition = "decimal(9,6)") protected BigDecimal lat;

    @JsonIgnore
    @Column(columnDefinition = "decimal(9,6)") protected BigDecimal lon;

    @JsonIgnore
    @Column(length = 50) protected String baiduname;

    @JsonIgnore
    @Column(length = 255) protected String description;

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

    // double minX, double maxX, double minY, double maxY
    @JsonIgnore
    public double[] getOutBox() {
        if (StringUtils.isNotBlank(coordinate)) {
            double[] result = {180d, -180d, 90d, -90d};
            try {
                String[] arr = coordinate.split("\\|");
                for (String s : arr) {
                    if (StringUtils.isNotBlank(s)) {
                        String[] parr = s.split(";");
                        for (String p : parr) {
                            int idx = p.indexOf(',');
                            double lon = Double.valueOf(p.substring(0, idx).trim());
                            double lat = Double.valueOf(p.substring(idx + 1).trim());
                            result[0] = Math.min(result[0], lon);
                            result[1] = Math.max(result[1], lon);
                            result[2] = Math.min(result[2], lat);
                            result[3] = Math.max(result[3], lat);
                        }
                    }
                }

            } catch (Exception e) {
            }
            return result;
        } else {
            return null;
        }
    }

}
