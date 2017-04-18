package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.vo.product.backend.ProductVo;
import com.google.common.base.Function;

/**
 * 转换器 (ProductVo ---> Product)
 * 没有设置 Brand, Category, ProductExtend, SaleTag
 *
 * @author david-liu
 * @date 2016年12月19日
 * @reviewer
 * @see
 */
public class ProductVo2Product implements Function<ProductVo, Product> {

    @Override
    public Product apply(ProductVo productVo) {
        Product product = new Product();
        product.setVendorId(productVo.getVendorId());
        product.setName(productVo.getName());
        product.setSubTitle(productVo.getSubTitle());
        product.setProductCode(productVo.getProductCode());
        product.setI18nCode(productVo.getI18nCode());
        product.setBreif(productVo.getBreif());
        //        product.setIntroImages(productVo.getIntroImages()); TODO
        product.setLogo(productVo.getLogo());
        product.setImages(productVo.getImages());
        product.setSeoTitle(productVo.getSeoTitle());
        product.setSeoKeywords(productVo.getSeoKeywords());
        product.setSeoDescription(productVo.getSeoDescription());
        return product;
    }
}
