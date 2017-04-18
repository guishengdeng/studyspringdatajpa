package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.dao.mysql.po.product.ProductAudit;
import com.google.common.base.Function;

/**
 * 转换器(ProductAudit --> Product)
 *
 * @author david-liu
 * @date 2016年12月26日
 * @reviewer
 * @see
 */
public class ProductAudit2Product implements Function<ProductAudit, Product> {
    @Override
    public Product apply(ProductAudit productAudit) {
        Product product = new Product();
        product.setId(productAudit.getProductId());
        product.setVendorId(productAudit.getVendorId());
        product.setName(productAudit.getName());
        product.setSubTitle(productAudit.getSubTitle());
        product.setProductCode(productAudit.getProductCode());
        product.setI18nCode(productAudit.getI18nCode());
        product.setBreif(productAudit.getBreif());
        product.setBrand(productAudit.getBrand());
        product.setCategory(productAudit.getCategory());
        product.setProperties(productAudit.getProperties());
        product.setIntroImages(productAudit.getIntroImages());
        product.setLogo(productAudit.getLogo());
        product.setImages(productAudit.getImages());
        product.setSaleTags(productAudit.getSaleTags());
        product.setSeoTitle(productAudit.getSeoTitle());
        product.setSeoKeywords(productAudit.getSeoKeywords());
        product.setSeoDescription(productAudit.getSeoDescription());
        return product;
    }
}
