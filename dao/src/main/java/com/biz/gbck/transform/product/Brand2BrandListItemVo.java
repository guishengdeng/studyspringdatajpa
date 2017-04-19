package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.Brand;
import com.biz.gbck.vo.product.backend.BrandListItemVo;
import com.google.common.base.Function;
import org.apache.commons.lang3.StringUtils;

/**
 * 转换器 (Brand --> BrandListItemVo)
 * 未设置分类名称
 *
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/19
 */
public class Brand2BrandListItemVo implements Function<Brand, BrandListItemVo> {

    private String categoryName;

    public Brand2BrandListItemVo(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public BrandListItemVo apply(Brand po) {
        BrandListItemVo vo = new BrandListItemVo();
        vo.setId(String.valueOf(po.getId()));
        vo.setName(po.getName());
        if (StringUtils.isBlank(categoryName)) {
            if (po.getCategories().size() == 0) {
                vo.setCategoryName("无分类");
            } else {
                vo.setCategoryName(po.getCategories().get(0).getName());
            }
        } else {
            vo.setCategoryName(categoryName);
        }
        vo.setIdx(po.getIdx());
        vo.setStatus(po.getStatus());
        return vo;
    }
}
