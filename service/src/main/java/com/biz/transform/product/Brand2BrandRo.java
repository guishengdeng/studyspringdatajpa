package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.Brand;
import com.biz.gbck.dao.redis.ro.product.BrandRo;
import com.google.common.base.Function;

/**
 * 转换器(Brand --> BrandRo)
 *
 * @author david-liu
 * @date 2017年01月23日
 * @reviewer
 */
public class Brand2BrandRo implements Function<Brand, BrandRo> {
    @Override
    public BrandRo apply(Brand po) {
        BrandRo ro = new BrandRo();
        ro.setId(po.getId());
        ro.setName(po.getName());
        ro.setNameEn(po.getNameEn());
        ro.setBrandCode(po.getBrandCode());
        ro.setLogo(po.getLogo());
        ro.setDescription(po.getDescription());
        ro.setIdx(po.getIdx());
        ro.setStatus(po.getStatus());
        ro.setSeoTitle(po.getSeoTitle());
        ro.setSeoKeywords(po.getSeoKeywords());
        ro.setSeoDescription(po.getSeoDescription());
        return ro;
    }
}
