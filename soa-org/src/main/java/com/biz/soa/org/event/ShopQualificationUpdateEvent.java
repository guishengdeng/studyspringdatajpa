package com.biz.soa.org.event;


import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;
import org.springframework.context.ApplicationEvent;

/**
 * 商户更新资质
 */

public class ShopQualificationUpdateEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1494715564592093444L;

    private ShopQualificationPo shopQualificationPo;

    public ShopQualificationUpdateEvent(Object source, ShopQualificationPo shopQualificationPo) {
        super(source);
        this.shopQualificationPo = shopQualificationPo;
    }

    public void setShopQualificationPo(ShopQualificationPo shopQualificationPo) {
        this.shopQualificationPo = shopQualificationPo;
    }

    public ShopQualificationPo getShopQualificationPo() {
        return shopQualificationPo;
    }
}
