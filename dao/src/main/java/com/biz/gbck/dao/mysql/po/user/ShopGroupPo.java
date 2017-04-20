package com.biz.gbck.dao.mysql.po.user;

import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

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
    private Set<ShopPo> shopPos;

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

    /**
     * {@linkplain ShopGroupPo#shopPos}
     */
    public Set<ShopPo> getShopPos() {

        return shopPos;
    }

    /**
     * {@linkplain ShopGroupPo#shopPos}
     */
    public void setShopPos(Set<ShopPo> shopPos) {

        this.shopPos = shopPos;
    }
}
