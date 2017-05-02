package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.vo.product.backend.CategoryTreeViewVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author yangzichun
 * @date 2017/4/28
 */
public class Category2CategoryTreeViewVo implements Function<Category, CategoryTreeViewVo> {
    @Override
    public CategoryTreeViewVo apply(Category category) {
        if (!category.getDeleteFlag()) {
            CategoryTreeViewVo categoryTreeViewVo = new CategoryTreeViewVo();
            categoryTreeViewVo.setId(category.getId().toString());
            categoryTreeViewVo.setName(category.getName());
            categoryTreeViewVo.setStatus(category.getStatus());
            List<CategoryTreeViewVo> categoryTreeViewVos = Lists.newArrayList();
            for (int i = 0; i < category.getChildren().size(); i++) {
                if (!category.getChildren().get(i).getDeleteFlag()) {
                    categoryTreeViewVos.add(new Category2CategoryTreeViewVo().apply(category.getChildren().get(i)));
                }
            }
            categoryTreeViewVo.setChildren(categoryTreeViewVos);
            return categoryTreeViewVo;
        } else {
            return null;
        }
    }
}
