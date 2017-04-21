package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.ProductFilter;
import com.biz.gbck.dao.redis.ro.product.meta.ProductFilterRo;
import com.google.common.base.Function;

/**
 * 转换器(ProductFilter --> ProductFilterRo)
 *
 * @author david-liu
 * @date 2017年01月24日
 * @reviewer
 */
public class ProductFilter2ProductFilterRo implements Function<ProductFilter, ProductFilterRo> {
    @Override
    public ProductFilterRo apply(ProductFilter po) {
        ProductFilterRo ro = new ProductFilterRo();
        ro.setId(po.getId());
        ro.setCategoryId(po.getCategory().getId());
        ro.setIdx(po.getIdx());
        ro.setLabel(po.getLabel());
        ro.setField(po.getField());
        ro.setUsePrefix(po.getUsePrefix());
        ro.setHasMore(po.getHasMore());
        ro.setShowImage(po.getShowImage());
        ro.setStatus(po.getStatus());
        return ro;
    }
}
