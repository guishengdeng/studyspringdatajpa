package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.meta.ProductFilter;
import com.biz.gbck.vo.product.backend.ProductFilterListItemVO;
import com.google.common.base.Function;

/**
 * @author wangyumin
 * @date 2016年1月4日
 */
public class ProductFilter2ProductFilterListItemVo implements Function<ProductFilter, ProductFilterListItemVO> {

    @Override
    public ProductFilterListItemVO apply(ProductFilter input) {
        ProductFilterListItemVO vo = new ProductFilterListItemVO();
        vo.setId(String.valueOf(input.getId()));
        vo.setField(input.getField());
        vo.setHasMore(input.getHasMore());
        vo.setIdx(input.getIdx());
        vo.setLabel(input.getLabel());
        vo.setShowImage(input.getShowImage());
        vo.setStatus(input.getStatus());
        vo.setUsePrefix(input.getUsePrefix());
        return vo;
    }
}
