package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.vo.product.backend.VendorDeletedProductListItemVo;
import com.google.common.base.Function;

/**
 * 转换器(商品 Po --> 商家已删除商品列表项 Vo)
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
public class Product2VendorDeletedProductListItemVo implements Function<Product, VendorDeletedProductListItemVo> {
    @Override
    public VendorDeletedProductListItemVo apply(Product product) {
        VendorDeletedProductListItemVo vo = new VendorDeletedProductListItemVo();
        vo.setId(String.valueOf(product.getId()));
        vo.setProductCode(product.getProductCode());
        vo.setProductName(product.getName());
        vo.setDeleteTimestamp(product.getUpdateTimestamp());
        vo.setLocked(product.getLocked());
        return vo;
    }
}
