package com.biz.gbck.dao.mysql.po.user;

import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 客户组
 *
 * @author: liubin
 * @date 4/20/17 09:28
 */
@Entity
@Table(name = "shop_group")
public class ShopGroupPo extends BaseEntity {

    /**
     * 客户组编码
     */
    private String code;

    /**
     * 客户组名称
     */
    private String name;

    /**
     * 商铺客户
     */
    @OneToMany(mappedBy = "shopGroupPo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ShopPo shopPo;

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

    public ShopPo getShopPo() {
        return shopPo;
    }

    public void setShopPo(ShopPo shopPo) {
        this.shopPo = shopPo;
    }
}
