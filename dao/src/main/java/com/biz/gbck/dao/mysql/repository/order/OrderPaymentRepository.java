package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.order.OrderPayment;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
@Repository
public interface OrderPaymentRepository extends CommonJpaRepository<OrderPayment, Long>, OrderPaymentDao,
        JpaSpecificationExecutor<OrderPayment> {

    @Modifying
    @Query("update OrderPayment op set op.payStatus = ?2 where op.id = ?1")
    void updatePaymentState(Long id, Integer value);
}
