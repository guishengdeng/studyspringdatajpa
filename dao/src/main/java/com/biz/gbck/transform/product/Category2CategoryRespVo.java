package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.vo.product.backend.CategoryRespVo;

import java.util.function.Function;

/**
 * @author yangzichun
 * @date 2017/4/25
 */
public class Category2CategoryRespVo implements Function<Category, CategoryRespVo> {
    @Override
    public CategoryRespVo apply(Category category) {
        CategoryRespVo respVo = new CategoryRespVo();
        respVo.setId(category.getId());
        respVo.setIdx(category.getIdx());
        respVo.setLogo(category.getLogo());
        respVo.setName(category.getName());
        respVo.setParent(category.getParent());
        respVo.setStatus(category.getStatus());
        respVo.setSeoDescription(category.getSeoDescription());
        respVo.setSeoKeywords(category.getSeoKeywords());
        respVo.setSeoTitle(category.getSeoTitle());
        return respVo;
    }
}
