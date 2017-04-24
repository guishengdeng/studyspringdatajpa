package com.biz.gbck.dao.mysql.repository.bbc.product;

import com.biz.gbck.dao.mysql.po.product.bbc.ProductAuditActionLog;

/**
 * 商品审核日志 Repository
 *
 * @author david-liu
 * @date 2016年12月23日
 * @reviewer
 * @see
 */
//@Repository
//public interface ProductAuditActionLogRepository extends CommonJpaRepository<ProductAuditActionLog, Long>, ProductAuditActionLogDao {
public interface ProductAuditActionLogRepository {
    ProductAuditActionLog findById(Long productAuditId);
}
