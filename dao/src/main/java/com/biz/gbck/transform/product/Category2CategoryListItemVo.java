package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.Category;
import com.biz.gbck.vo.product.backend.CategoryListItemVo;
import com.google.common.base.Function;

/**
 * 转换器(Category ---> CategoryListItemVo)
 *
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/19
 */
public class Category2CategoryListItemVo implements Function<Category, CategoryListItemVo> {

    @Override
    public CategoryListItemVo apply(Category category) {
        CategoryListItemVo categoryListItemVo = new CategoryListItemVo();
        categoryListItemVo.setId(String.valueOf(category.getId()));
        categoryListItemVo.setName(category.getName());
        categoryListItemVo.setIdx(category.getIdx());
        categoryListItemVo.setCreateTimeStamp(category.getCreateTimestamp());
        categoryListItemVo.setStatus(category.getStatus());
        if ((category.getChildren()).size() == 0) {
            categoryListItemVo.setShowDel("true");
        } else {
            categoryListItemVo.setShowDel("false");
        }
        return categoryListItemVo;
    }
}
