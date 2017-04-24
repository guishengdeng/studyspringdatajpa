package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.ProductFilter;
import com.biz.gbck.vo.product.backend.CreateProductFilterVo;
import com.google.common.base.Function;

public class CreateProductFilterVo2ProductFilter implements Function<CreateProductFilterVo, ProductFilter> {

    @Override
    public ProductFilter apply(CreateProductFilterVo createVo) {
        ProductFilter productFilter = new ProductFilter();
        productFilter.setId(createVo.getId());
        productFilter.setField(createVo.getField());
        productFilter.setFilterItems(null);
        productFilter.setHasMore(createVo.getHasMore());
        productFilter.setLabel(createVo.getLabel());
        productFilter.setShowImage(createVo.getShowImage());
        productFilter.setStatus(createVo.getStatus());
        productFilter.setUsePrefix(createVo.getUsePrefix());
        return productFilter;
    }

}
