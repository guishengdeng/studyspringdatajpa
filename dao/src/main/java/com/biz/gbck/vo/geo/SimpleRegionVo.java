package com.biz.gbck.vo.geo;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by defei on 3/28/16.
 */
public class SimpleRegionVo {

    private Integer id;

    private String name;

    private Integer level;

    public SimpleRegionVo() {
    }

    public SimpleRegionVo(Integer id, String name, Integer level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
