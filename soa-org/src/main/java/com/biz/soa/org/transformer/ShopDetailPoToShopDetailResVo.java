package com.biz.soa.org.transformer;

import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.vo.org.ShopDetailResVo;
import com.google.common.base.Function;

import java.io.Serializable;

@SuppressWarnings("serial") public class ShopDetailPoToShopDetailResVo
    implements Function<ShopDetailPo, ShopDetailResVo>, Serializable {

    @Override
    public ShopDetailResVo apply(ShopDetailPo input) {
        ShopDetailResVo vo=null;
        if(input != null){
            vo=new ShopDetailResVo();
            vo.setShopDetailId(input.getId());
            if(input.getShop() != null){
                vo.setShopId(input.getShop().getId());
                if(input.getShop().getStatus() != null){
                    vo.setShopStatus(input.getShop().getStatus().name());
                }
            }
            vo.setName(input.getName());
            if(input.getShopType() != null){
                vo.setShopTypeName(input.getShopType().getName());
            }
            vo.setShopAddress(input.getShopAddress());
            vo.setMobile(input.getMobile());
            vo.setAuditStatus(input.getAuditStatus());
            vo.setCreateTime(input.getCreateTime());
            vo.setDeliveryAddress(input.getDeliveryAddress());
        }
        return vo;
    }
}
