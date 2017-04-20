package com.biz.gbck.dao.mysql.repository.product;

import com.biz.gbck.dao.mysql.po.product.bbc.ProductAuditActionLog;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 商品审核日志 Repository
 *
 * @author david-liu
 * @date 2016年12月23日
 * @reviewer
 * @see
 */
@Repository
public interface ProductAuditActionLogRepository extends CommonJpaRepository<ProductAuditActionLog, Long>, ProductAuditActionLogDao {
    ProductAuditActionLog findById(Long productAuditId);
}
