package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
@Repository
public interface OrderRepository extends CommonJpaRepository<Order, Long>, OrderDao, JpaSpecificationExecutor<Order> {
}
