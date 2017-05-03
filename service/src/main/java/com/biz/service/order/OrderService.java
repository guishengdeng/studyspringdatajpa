package com.biz.service.order;

import com.biz.gbck.vo.order.OrderListReqVo;
import com.biz.gbck.vo.order.OrderRespVo;

import java.util.List;

/**
 * OrderService
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
public interface OrderService {
    List<OrderRespVo> listOrders(OrderListReqVo reqVo);
}
