package com.biz.gbck.dao.mysql.repository.productActionLog;

import com.biz.gbck.dao.mysql.po.product.master.ProductActionLog;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 商品维护记录Repository
 *
 * Created by david-liu on 2017/04/21 11:32.
 */
public interface ProductActionLogRepository extends CommonJpaRepository<ProductActionLog, Long>,
        ProductActionLogDao, JpaSpecificationExecutor<ProductActionLog> {
}
