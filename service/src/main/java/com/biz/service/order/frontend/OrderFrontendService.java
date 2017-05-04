package com.biz.service.order.frontend;

import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.order.req.OrderCreateReqVo;
import com.biz.gbck.vo.order.req.OrderListReqVo;
import com.biz.gbck.vo.order.req.OrderSettlePageReqVo;
import com.biz.gbck.vo.order.resp.OrderCreateRespVo;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;

import java.util.List;

/**
 * OrderService
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
public interface OrderFrontendService {

    List<OrderRespVo> listOrders(OrderListReqVo reqVo);

    OrderRespVo getOrderDetail(IdReqVo reqVo);

    void cancelOrder(IdReqVo reqVo);

    OrderCreateRespVo createOrder(OrderCreateReqVo reqVo);

    OrderSettlePageRespVo settle(OrderSettlePageReqVo reqVo);
}
