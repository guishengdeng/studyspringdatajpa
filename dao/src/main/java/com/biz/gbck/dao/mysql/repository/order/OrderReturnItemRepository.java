package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.order.OrderReturnItem;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
@Repository
public interface OrderReturnItemRepository extends CommonJpaRepository<OrderReturnItem, Long>, OrderReturnItemDao,
        JpaSpecificationExecutor<OrderReturnItem> {

    List<OrderReturnItem> findByOrderReturn_ReturnCode(String returnCode);
}
