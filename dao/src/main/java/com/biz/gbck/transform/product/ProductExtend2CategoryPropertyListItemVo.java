package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.ProductExtend;
import com.biz.gbck.vo.product.backend.CategoryPropertyListItemVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author wangyumin
 * @date 2016年12月27日
 */

public class ProductExtend2CategoryPropertyListItemVo implements Function<ProductExtend, CategoryPropertyListItemVo> {
    @Override
    public CategoryPropertyListItemVo apply(ProductExtend productExtend) {
        CategoryPropertyListItemVo categoryPropertyListItemVo = new CategoryPropertyListItemVo();
        categoryPropertyListItemVo.setId(productExtend.getId().toString());
        categoryPropertyListItemVo.setName(productExtend.getName());
        categoryPropertyListItemVo.setIdx(productExtend.getIdx());
        categoryPropertyListItemVo.setStatus(productExtend.getStatus());
        categoryPropertyListItemVo.setExtendProperty(Lists.transform(productExtend.getProperties(), new ExtendProperty2ExtendPropertyVo()));
        return categoryPropertyListItemVo;
    }
}
