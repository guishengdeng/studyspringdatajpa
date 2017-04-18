package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.GeoProduct;
import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.vo.product.backend.ProductDetailVo;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/3/6
 */
public class GeoProduct2ProductDetailVo implements Function<GeoProduct, ProductDetailVo> {
    @Nullable
    @Override
    public ProductDetailVo apply(@Nullable GeoProduct input) {
        Product product = input.getProduct();
        ProductDetailVo resp = new ProductDetailVo();
        resp.setProductId(String.valueOf(product.getId()));
        resp.setProductCode(product.getProductCode());
        resp.setProductName(product.getName());
        resp.setSubTitle(product.getSubTitle());
        resp.setVendorId(String.valueOf(product.getVendorId()));
        resp.setProductType(product.getProductType().getValue());
        resp.setProductImages(product.getImages());
        //        resp.setCountryStock(input.get);
        resp.setMarketPrice(input.getMarketPrice());
        resp.setFinalPrice(input.getSalePrice());
        resp.setWeight(product.getWeight());
        return resp;
    }
}
