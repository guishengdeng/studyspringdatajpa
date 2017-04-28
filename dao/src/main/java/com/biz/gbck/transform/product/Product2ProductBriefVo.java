package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.gbck.vo.product.backend.ProductBriefVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/7
 */
public class Product2ProductBriefVo implements Function<Product, ProductBriefVo> {

    @Override
    public ProductBriefVo apply(Product input) {
        return new ProductBriefVo(String.valueOf(input.getId()), input.getProductCode(), input.getName(), input.getCategory().getName(), input.getLogo(), String.valueOf(input.getCategory().getId()));
    }

}
