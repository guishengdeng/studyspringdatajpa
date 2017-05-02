package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.vo.product.backend.UpdateCategoryVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/22
 */
public class Category2UpdateCategoryVo implements Function<Category, UpdateCategoryVo> {
    @Override
    public UpdateCategoryVo apply(Category input) {

        UpdateCategoryVo updateCategoryVo = new UpdateCategoryVo();
        updateCategoryVo.setSeoTitle(input.getSeoTitle());
        updateCategoryVo.setSeoKeywords(input.getSeoKeywords());
        updateCategoryVo.setLogo(input.getLogo());
        updateCategoryVo.setSeoDescription(input.getSeoDescription());
        updateCategoryVo.setId(input.getId());
        updateCategoryVo.setName(input.getName());
        updateCategoryVo.setStatus(input.getStatus());
        if (input.getParent() != null) {
            updateCategoryVo.setParentCategoryId(input.getParent().getId());
        }
        return updateCategoryVo;
    }
}
