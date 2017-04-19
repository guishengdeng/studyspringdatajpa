package com.biz.gbck.dao.mysql.repository.product;

import com.biz.gbck.dao.mysql.po.product.ProductAudit;
import com.biz.gbck.enums.product.ProductAuditStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 商品审核记录 Repository
 *
 * @author david-liu
 * @date 2016年12月23日
 * @reviewer
 * @see
 */
@Repository
public interface ProductAuditRepository extends CommonJpaRepository<ProductAudit, Long>, ProductAuditDao, JpaSpecificationExecutor<ProductAudit> {

    ProductAudit findByProductIdAndDeleteFlag(Long productId, Boolean deleteFlag);

    Page<ProductAudit> findByAuditStatusNotAndDeleteFlag(ProductAuditStatusEnum productAuditStatus,
                                                         Boolean deleteFlag, Pageable pageable);

    @Query("FROM ProductAudit p WHERE p.vendorId = ?1 AND p.category.id = ?2 AND p.deleteFlag = ?3")
    Page<ProductAudit> findByVendorIdAndCategoryIdAndDeleteFlag(Long vendorId, Long categoryId, Boolean deleteFlag, Pageable pageable);

    Page<ProductAudit> findByVendorIdAndDeleteFlag(Long vendorId, Boolean deleteFlag, Pageable pageable);

    @Query("FROM ProductAudit p WHERE p.category.id = ?1 AND p.deleteFlag = ?2")
    Page<ProductAudit> findByCategoryIdAndDeleteFlag(Long categoryId, Boolean deleteFlag, Pageable pageable);

    Page<ProductAudit> findByDeleteFlag(Boolean deleteFlag, Pageable pageable);

    ProductAudit findByProductIdAndVendorIdAndDeleteFlag(Long productId, Long vendorId, Boolean deleteFlag);

    ProductAudit findByIdAndDeleteFlag(Long id, Boolean deleteFlag);

    List<ProductAudit> findByProductCodeAndVendorId(String productCode, Long vendorId);

    ProductAudit findByProductId(Long id);
}
