package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.bbc.GeoProduct;
import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.gbck.vo.product.backend.ProductDetailVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/3/6
 */
public class GeoProduct2ProductDetailVo implements Function<GeoProduct, ProductDetailVo> {

    @Override
    public ProductDetailVo apply(GeoProduct input) {
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
