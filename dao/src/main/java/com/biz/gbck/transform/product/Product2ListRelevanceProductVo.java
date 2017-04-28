package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.bbc.GeoProduct;
import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.gbck.dao.mysql.repository.bbc.product.GeoProductRepository;
import com.biz.gbck.vo.product.backend.ListRelevanceProductVo;
import com.google.common.base.Function;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/25
 */
public class Product2ListRelevanceProductVo implements Function<Product, ListRelevanceProductVo> {

    private GeoProductRepository geoProductRepository;

    public Product2ListRelevanceProductVo(GeoProductRepository geoProductRepository) {
        this.geoProductRepository = geoProductRepository;
    }

    @Override
    public ListRelevanceProductVo apply(Product product) {
        ListRelevanceProductVo respVo = new ListRelevanceProductVo();
        respVo.setProductCode(product.getProductCode());
        respVo.setCategoryName(product.getCategory().getName());
        respVo.setProductName(product.getName());
        GeoProduct geoProduct = geoProductRepository.findByProduct(product);
        if (geoProduct == null) {

        } else {
            respVo.setSaleStatus(geoProduct.getSaleStatus());
        }
        return respVo;
    }
}
