package com.biz.gbck.dao.mysql.repository.purchase;

import com.biz.gbck.dao.mysql.po.purchase.PurchaseReturnOrder;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
@Repository
public interface PurchaseReturnOrderRepository extends CommonJpaRepository<PurchaseReturnOrder, Long>,
        PurchaseReturnOrderDao, JpaSpecificationExecutor<PurchaseReturnOrder> {
}
