package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.ProductExtend;
import com.biz.gbck.vo.product.backend.CreateCategoryPropertyVo;
import com.google.common.base.Function;

public class CreateCategoryPropertyVo2ProductExtend implements Function<CreateCategoryPropertyVo, ProductExtend> {
    @Override
    public ProductExtend apply(CreateCategoryPropertyVo createCategoryPropertyVo) {
        ProductExtend productExtend = new ProductExtend();
        productExtend.setId(createCategoryPropertyVo.getId());
        productExtend.setName(createCategoryPropertyVo.getName());
        productExtend.setIdx(createCategoryPropertyVo.getIdx());
        productExtend.setStatus(createCategoryPropertyVo.getStatus());
        productExtend.setProperties(null);
        return productExtend;
    }
}
