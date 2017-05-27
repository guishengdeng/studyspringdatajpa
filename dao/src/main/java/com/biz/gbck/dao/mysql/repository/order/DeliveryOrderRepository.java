package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.purchase.DeliveryOrder;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * DeliveryOrderRepository
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
@Repository
public interface DeliveryOrderRepository extends CommonJpaRepository<DeliveryOrder,Long>,DeliveryOrderDao,JpaSpecificationExecutor<DeliveryOrder>{

    DeliveryOrder  findByOrderCode(String orderCode);
}
