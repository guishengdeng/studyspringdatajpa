package com.biz.vo.org;


import com.biz.gbck.dao.mysql.po.org.ShopTypePo;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.gbck.enums.user.ShopWeight;

/**
 * Created by defei on 3/16/16.
 */
public class ShopTypeCreateReqVo {

    /**
     * 名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer idx = 0;

    /**
     * 备注
     */
    private String description;

    public ShopTypePo toShopTypePo() {

        ShopTypePo shopTypePo = new ShopTypePo();
        shopTypePo.setName(name);
        shopTypePo.setDescription(description);
        shopTypePo.setIdx(idx);
        shopTypePo.setWeight(ShopWeight.NORMARL.getValue());
        shopTypePo.setStatus(ShopTypeStatus.NORMAL.getValue());
        return shopTypePo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
