package com.biz.event.org;


import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;

/**
 * 商户更新详细资料
 */

public class ShopDetailUpdateEvent extends ShopEvent {

    private ShopDetailPo shopDetailPo;

    public ShopDetailUpdateEvent(Object source, ShopDetailPo shopDetailPo) {
        super(source);
        this.shopDetailPo = shopDetailPo;
    }

    public ShopDetailPo getShopDetailPo() {
        return shopDetailPo;
    }
}
