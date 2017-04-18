package com.biz.gbck.dao.mysql.repository.product;

import com.biz.gbck.dao.mysql.po.product.MnsProduct;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhangcheng
 * @date 2017/1/5
 * @reviewer
 * @see
 */
@Repository
public interface MnsProductRepository extends CommonJpaRepository<MnsProduct, Long>, MnsProductDao {

    @Query("FROM MnsProduct p WHERE p.matnr = ?1")
    MnsProduct findBySku(String sku);

    @Query("FROM MnsProduct p ORDER BY p.lastmodifytime DESC")
    Page<MnsProduct> findAllByLastModifyTime(Pageable pageable);

    @Query("FROM MnsProduct p WHERE p.matnr LIKE %:productCode% ORDER BY p.lastmodifytime DESC")
    Page<MnsProduct> findBySkuLike(@Param("productCode") String productCode, Pageable pageable);
}
