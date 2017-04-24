package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.Brand;
import com.biz.gbck.vo.product.backend.UpdateBrandVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/22
 */
public class Brand2UpdateBrandVo implements Function<Brand, UpdateBrandVo> {
    @Override
    public UpdateBrandVo apply(Brand input) {
        UpdateBrandVo updateBrandVo = new UpdateBrandVo();
        updateBrandVo.setName(input.getName());
        updateBrandVo.setStatus(input.getStatus());
        updateBrandVo.setBrandCode(input.getBrandCode());
        if (input.getCategories() == null) {
            throw new RuntimeException("该品牌没有分类信息,为脏数据");
        }
        if (input.getCategories().get(0) == null) {
            throw new RuntimeException("该品牌没有分类");
        }
        updateBrandVo.setCategoryId(input.getCategories().get(0).getId());
        updateBrandVo.setId(input.getId());
        updateBrandVo.setLogo(input.getLogo());
        updateBrandVo.setNameEn(input.getNameEn());
        updateBrandVo.setSeoTitle(input.getSeoTitle());
        updateBrandVo.setSeoDescription(input.getSeoDescription());
        updateBrandVo.setSeoKeywords(input.getSeoKeywords());
        updateBrandVo.setDescription(input.getDescription());
        return updateBrandVo;
    }
}
