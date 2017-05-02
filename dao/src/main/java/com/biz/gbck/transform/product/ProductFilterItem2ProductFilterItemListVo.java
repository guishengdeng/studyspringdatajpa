package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.ProductFilterItem;
import com.biz.gbck.vo.product.backend.ProductFilterItemListVo;
import com.google.common.base.Function;

public class ProductFilterItem2ProductFilterItemListVo implements Function<ProductFilterItem, ProductFilterItemListVo> {

    @Override
    public ProductFilterItemListVo apply(ProductFilterItem input) {
        ProductFilterItemListVo list = new ProductFilterItemListVo();
        list.setId(input.getId().toString());
        list.setHighlightShow(input.getHighlightShow());
        list.setIdx(input.getIdx());
        list.setLabel(input.getLabel());
        list.setPrefix(input.getPrefix());
        list.setStatus(input.getStatus());
        list.setValue(input.getValue());
        return list;
    }

}
