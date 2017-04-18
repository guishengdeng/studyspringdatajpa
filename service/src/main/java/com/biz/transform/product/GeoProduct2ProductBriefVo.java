package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.GeoProduct;
import com.biz.gbck.vo.product.backend.ProductBriefVo;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/13
 */
public class GeoProduct2ProductBriefVo implements Function<GeoProduct, ProductBriefVo> {

    @Nullable
    @Override
    public ProductBriefVo apply(@Nullable GeoProduct input) {
        return new ProductBriefVo(String.valueOf(input.getProduct().getId()), input.getProduct().getProductCode(), input.getProduct().getName(), input.getProduct().getCategory().getName());
    }
}
