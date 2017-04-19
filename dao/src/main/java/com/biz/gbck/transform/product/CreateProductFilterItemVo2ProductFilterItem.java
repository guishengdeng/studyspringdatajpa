package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.ProductFilterItem;
import com.biz.gbck.vo.product.backend.CreateProductFilterItemVo;
import com.google.common.base.Function;

public class CreateProductFilterItemVo2ProductFilterItem implements Function<CreateProductFilterItemVo, ProductFilterItem> {

    @Override
    public ProductFilterItem apply(CreateProductFilterItemVo input) {
        ProductFilterItem result = new ProductFilterItem();
        result.setHighlightShow(input.getHighlightShow());
        result.setImage(input.getImage());
        result.setLabel(input.getLabel());
        result.setPrefix(input.getPrefix());
        result.setStatus(input.getStatus());
        result.setValue(input.getValue());
        return result;
    }

}
