package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.dao.mysql.po.product.ProductAudit;
import com.google.common.base.Function;
import org.codelogger.utils.BeanUtils;

/**
 * 商品Po转换为商品审核Po
 *
 * @author zhangcheng
 * @date 2017/3/21
 * @reviewer
 * @see
 */
public class Product2ProductAudit implements Function<Product, ProductAudit> {

    @Override
    public ProductAudit apply(Product product) {
        if (product != null) {
            ProductAudit productAudit = new ProductAudit();
            BeanUtils.copyProperties(productAudit, product);
            return productAudit;
        }
        return null;
    }
}
