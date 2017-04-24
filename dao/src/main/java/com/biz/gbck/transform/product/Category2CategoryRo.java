package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.Category;
import com.biz.gbck.dao.redis.ro.product.CategoryRo;
import com.google.common.base.Function;

/**
 * @author david-liu
 * @date 2017年01月23日
 * @reviewer
 * @see
 */
public class Category2CategoryRo implements Function<Category, CategoryRo> {

    @Override
    public CategoryRo apply(Category po) {
        CategoryRo ro = new CategoryRo();
        ro.setId(po.getId());
        ro.setName(po.getName());
        ro.setIdx(po.getIdx());
        ro.setLogo(po.getLogo());
        ro.setStatus(po.getStatus());
        if (po.getParent() != null) {
            ro.setParentId(po.getParent().getId());
        }
        ro.setSeoTitle(po.getSeoTitle());
        ro.setSeoKeywords(po.getSeoKeywords());
        ro.setSeoDescription(po.getSeoDescription());
        return ro;
    }

}
