package com.biz.gbck.dao.redis.ro.org;


import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

import java.io.Serializable;

/**
 * Created by defei on 3/18/16.
 */
@Ro(key = "org:shopTypeRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class ShopTypeRo  extends BaseRedisObject<String> implements Serializable {



    /**
     * 名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer idx = 0;

    /**
     * 权重 (后台管理暂时不维护)
     */
    private Integer weight;

    /**
     * 状态(0：删除,1:正常)
     */
    private Integer status;

    /**
     * 备注
     */
    private String description;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}