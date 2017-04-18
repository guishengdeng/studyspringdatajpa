package com.biz.gbck.dao.mysql.repository.productCascade;

import com.biz.gbck.dao.mysql.po.product.ProductCascade;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 商品配置
 *
 * @author david-liu
 * @date 2016年12月28日
 * @reviewer
 * @see
 */
@Repository
public interface ProductCascadeRepository extends CommonJpaRepository<ProductCascade, Long>, ProductCascadeDao {

    ProductCascade findByIdAndVendorId(Long id, Long vendorId);

    List<ProductCascade> findByVendorId(Long vendorId);

    @Query("SELECT c FROM ProductCascade c join c.products p where p.id = ?2 AND c.vendorId = ?1")
    List<ProductCascade> findByVendorIdAndProductsId(Long vendorId, Long productId);

    ProductCascade findBySourceProductIdAndVendorId(Long productId, Long vendorId);
}
