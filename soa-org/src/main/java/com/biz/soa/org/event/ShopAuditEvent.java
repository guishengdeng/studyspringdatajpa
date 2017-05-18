package com.biz.soa.org.event;


import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;

/**
 * Created by JKLiues on 2016/5/11.
 */
public class ShopAuditEvent extends ShopEvent {
    private ShopDetailPo shopDetailPo;
    private ShopQualificationPo shopQualificationPo;

    public ShopAuditEvent(Object source, ShopDetailPo shopDetailPo,
                          ShopQualificationPo shopQualificationPo) {
        super(source);
        this.shopDetailPo = shopDetailPo;
        this.shopQualificationPo = shopQualificationPo;
    }

    public ShopDetailPo getShopDetailPo() {
        return shopDetailPo;
    }

    public ShopQualificationPo getShopQualificationPo() {
        return shopQualificationPo;
    }
}
