package com.biz.gbck.vo.geo;

/**
 * Created by defei on 3/28/16.
 */
public class SimpleRegionVo {

    private Integer id;

    private String name;

    private Integer level;

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
}
