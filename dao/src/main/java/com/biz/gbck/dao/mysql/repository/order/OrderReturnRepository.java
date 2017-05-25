package com.biz.gbck.dao.mysql.repository.order;

import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.enums.order.RefundStatus;
import com.biz.gbck.enums.order.ReturnStatus;
import com.biz.gbck.enums.order.ReturnType;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
@Repository
public interface OrderReturnRepository extends CommonJpaRepository<OrderReturn, Long>, OrderReturnDao,
        JpaSpecificationExecutor<OrderReturn> {

    OrderReturn findByReturnCode(String returnCode);

    List<OrderReturn> findByOrder_OrderCode(String Order_OrderCode);

    List<OrderReturn>  findByStatus(ReturnStatus returnStatus);

    List<OrderReturn> findByRefundStatus(RefundStatus refundStatus0);

    List<OrderReturn> findByReturnType(ReturnType returnType);

    List<OrderReturn> findByCreateTimestampBetween(Timestamp begin,Timestamp end);

}
