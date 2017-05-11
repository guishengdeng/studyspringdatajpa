package com.biz.gbck.transform.org;

import com.biz.gbck.dao.mysql.po.org.ShopTypePo;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.google.common.base.Function;

import java.io.Serializable;

public class ShopTypePoToShopTypeRo implements Function<ShopTypePo, ShopTypeRo>, Serializable {

    @Override public ShopTypeRo apply(ShopTypePo po) {
        ShopTypeRo ro = null;
        if (po != null) {
            ro = new ShopTypeRo();
            ro.setId(po.getId().toString());
            ro.setName(po.getName());
            ro.setIdx(po.getIdx());
            ro.setDescription(po.getDescription());
            ro.setWeight(po.getWeight());
            ro.setStatus(po.getStatus());
        }
        return ro;
    }

}
