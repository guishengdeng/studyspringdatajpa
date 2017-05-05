package com.biz.gbck.vo.org;


import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;

/**
 * Created by defei on 3/31/16.
 */
public class ShopAuditVo {

    private ShopDetailPo shopDetail;

    private ShopQualificationPo shopQualification;

    public ShopDetailPo getShopDetail() {
        return shopDetail;
    }

    public ShopPo getShop() {

        return shopDetail == null ?
            shopQualification == null ? null : shopQualification.getShop() :
            shopDetail.getShop();
    }

    public void setShopDetail(ShopDetailPo shopDetail) {
        this.shopDetail = this.shopDetail == null ? shopDetail : this.shopDetail;
    }

    public ShopQualificationPo getShopQualification() {
        return shopQualification;
    }

    public void setShopQualification(ShopQualificationPo shopQualification) {
        this.shopQualification =
            this.shopQualification == null ? shopQualification : this.shopQualification;
    }
}
