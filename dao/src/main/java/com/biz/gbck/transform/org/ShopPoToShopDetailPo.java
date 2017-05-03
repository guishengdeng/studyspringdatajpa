package com.biz.gbck.transform.org;

import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.google.common.base.Function;

import java.io.Serializable;

public class ShopPoToShopDetailPo implements Function<ShopPo, ShopDetailPo>, Serializable {


    @Override public ShopDetailPo apply(ShopPo shopPo) {
        ShopDetailPo shopDetailPo = null;
        if (shopPo != null) {
            shopDetailPo = new ShopDetailPo();
            shopDetailPo.setShop(shopPo);
            shopDetailPo.setAuditStatus(shopPo.getDetailAuditStatus());
            shopDetailPo.setName(shopPo.getName());
            shopDetailPo.setCorporateName(shopDetailPo.getCorporateName());
            shopDetailPo.setShopType(shopPo.getShopType());
            shopDetailPo.setMobile(shopPo.getMobile());
            shopDetailPo.setDeliveryName(shopPo.getDeliveryName());
            shopDetailPo.setDeliveryAddress(shopPo.getDeliveryAddress());
            shopDetailPo.setDeliveryMobile(shopPo.getDeliveryMobile());
            shopDetailPo.setProvince(shopPo.getProvince());
            shopDetailPo.setCity(shopPo.getCity());
            shopDetailPo.setDistrict(shopPo.getDistrict());
        }
        return shopDetailPo;
    }

}
