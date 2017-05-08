package com.biz.gbck.transform.org;

import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.vo.org.SearchShopRespVo;
import com.google.common.base.Function;

import java.io.Serializable;

/**
 * Created by JKLiues on 2016/5/4.
 */
public class ShopPoToSearchShopRespVo implements Function<ShopPo, SearchShopRespVo>, Serializable {
    @Override
    public SearchShopRespVo apply(ShopPo po) {
        SearchShopRespVo vo = new SearchShopRespVo();
        if (po == null)
            return vo;
        String deliveryAddress = "";
        vo.setId(po.getId());
        vo.setMobile(po.getMobile());
        vo.setName(po.getName());
        vo.setStatus(po.getStatus().getValue());
        vo.setShopAddress(po.getShopAddress());
        vo.setShopType(po.getShopType());
        vo.setAuditStatus(po.getDetailAuditStatus());
        if (po.getProvince() != null) {
            deliveryAddress += po.getProvince().getName() + "/";
        }
        if (po.getCity() != null) {
            deliveryAddress += po.getCity().getName() + "/";
        }
        if (po.getDistrict() != null) {
            deliveryAddress += po.getDistrict().getName() + "/";
        }
        if (po.getDeliveryAddress() != null) {
            deliveryAddress += po.getDeliveryAddress();
        } else {
            deliveryAddress += "尚无收货地址";
        }
        vo.setDeliveryAddress(deliveryAddress);
        return vo;
    }
}
