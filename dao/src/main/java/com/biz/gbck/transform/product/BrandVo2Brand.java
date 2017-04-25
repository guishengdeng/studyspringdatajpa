package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.Brand;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.BrandVo;
import com.google.common.base.Function;
import java.util.List;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/19
 */
public class BrandVo2Brand implements Function<BrandVo, Brand> {

    private Brand brand;

    private List<Category> categoryList;

    public BrandVo2Brand(Brand brand, List<Category> categoryList) {
        this.brand = brand;
        if (brand == null) {
            this.brand = new Brand();
        }
        this.categoryList = categoryList;
    }

    @Override
    public Brand apply(BrandVo input) {
        brand.setId(Long.valueOf(input.getId()));
        brand.setName(input.getName());
        brand.setNameEn(input.getNameEn());
        brand.setBrandCode(input.getBrandCode());
        brand.setLogo(input.getLogo());
        brand.setDescription(input.getDescription());
        brand.setIdx(input.getIdx());
        brand.setCategories(categoryList);
        brand.setStatus(input.getStatus() == CommonStatusEnum.DISABLE.getValue() ? CommonStatusEnum.DISABLE : CommonStatusEnum.ENABLE);
        brand.setSeoTitle(input.getSeoTitle());
        brand.setSeoKeywords(input.getSeoKeywords());
        brand.setSeoDescription(input.getSeoDescription());
        return brand;
    }
}
