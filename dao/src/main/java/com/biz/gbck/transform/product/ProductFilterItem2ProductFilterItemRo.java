package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.ProductFilterItem;
import com.biz.gbck.dao.redis.ro.product.ProductFilterItemRo;
import com.google.common.base.Function;

/**
 * 转换器(ProductFilterItem --> ProductFilterItemRo)
 *
 * @author david-liu
 * @date 2017年01月24日
 * @reviewer
 */
public class ProductFilterItem2ProductFilterItemRo implements Function<ProductFilterItem, ProductFilterItemRo> {
    @Override
    public ProductFilterItemRo apply(ProductFilterItem po) {
        ProductFilterItemRo ro = new ProductFilterItemRo();
        ro.setId(po.getId());
        ro.setFilterId(po.getProductFilter().getId());
        ro.setPrefix(po.getPrefix());
        ro.setLabel(po.getLabel());
        ro.setValue(po.getValue());
        ro.setImage(po.getImage());
        ro.setIdx(po.getIdx());
        ro.setHighlightShow(po.getHighlightShow());
        ro.setStatus(po.getStatus());
        return ro;
    }
}
