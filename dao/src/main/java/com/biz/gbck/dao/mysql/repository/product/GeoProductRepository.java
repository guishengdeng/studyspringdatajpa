package com.biz.gbck.dao.mysql.repository.product;

import com.biz.gbck.dao.mysql.po.product.bbc.GeoProduct;
import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 区域商品Repository
 *
 * @author david-liu
 * @date 2017年01月16日
 * @reviewer
 * @see
 */
@Repository
public interface GeoProductRepository extends CommonJpaRepository<GeoProduct, Long>, JpaSpecificationExecutor<GeoProduct> {

    GeoProduct findByProductId(Long id);

    GeoProduct findByProductIdAndVendorId(Long id, Long vendorId);

    GeoProduct findByProduct(Product product);

    List<GeoProduct> findByProductIdIn(List<Long> productIds);

    GeoProduct findByProductProductCode(String productCode);

    GeoProduct findByProductProductCodeAndVendorId(String productCode, Long aLong);
}
