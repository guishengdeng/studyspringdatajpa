package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.vo.product.backend.CategoryListItemVo;
import com.google.common.base.Function;

import java.text.SimpleDateFormat;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        categoryListItemVo.setCreateTimeStamp(simpleDateFormat.format(category.getCreateTimestamp()));
        categoryListItemVo.setStatus(category.getStatus());
        if ((category.getChildren()).size() == 0) {
            categoryListItemVo.setShowDel("true");
        } else {
            categoryListItemVo.setShowDel("false");
        }
        return categoryListItemVo;
    }
}
