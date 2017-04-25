package com.biz.gbck.dao.mysql.po.org;


import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.gbck.enums.user.ShopWeight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户类型/商户类型
 *
 * @author gongshutao
 */
@Entity @Table(name = "shop_type") public class ShopTypePo implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    /**
     * 名称
     */
    @Column(length = 50, nullable = false) private String name;

    /**
     * 显示顺序
     */
    @Column(nullable = false) private Integer idx = 0;

    /**
     * 权重 (后台管理暂时不维护)
     */
    @Column(nullable = false) private Integer weight = ShopWeight.NORMARL.getValue();


    /**
     * 状态(0：删除,1:正常)
     */
    @Column(nullable = false) private Integer status = ShopTypeStatus.NORMAL.getValue();

    /**
     * 备注
     */
    @Column(length = 255) private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
