package com.biz.service.order.backend;

import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.dao.mysql.po.order.OrderReturnItem;
import com.biz.gbck.vo.order.req.OrderReturnAuditReqVo;
import com.biz.gbck.vo.order.req.OrderReturnListReqVo;
import com.biz.gbck.vo.order.req.OrderReturnSearchReqVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by liqi1 on 2017/5/18.
 */
public interface OrderReturnBackendService {

    OrderReturn getOrderReturn(Long id);

    Page<OrderReturn> listOrderReturns(OrderReturnListReqVo reqVo);

    Page<OrderReturn> searchOrderReturn(OrderReturnSearchReqVo reqVo);

    void auditOrderReturn(OrderReturnAuditReqVo reqVo);

}
