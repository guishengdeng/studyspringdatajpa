package com.biz.service.order.frontend;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.PageRespVo;
import com.biz.gbck.vo.order.req.OrderApplyReturnReqVo;
import com.biz.gbck.vo.order.req.OrderCreateReqVo;
import com.biz.gbck.vo.order.req.OrderListReqVo;
import com.biz.gbck.vo.order.req.OrderSettlePageReqVo;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;

/**
 * OrderService
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
public interface OrderFrontendService {

    PageRespVo listOrders(OrderListReqVo reqVo) throws DepotNextDoorException;

    OrderRespVo getOrderDetail(IdReqVo reqVo) throws DepotNextDoorException;

    void cancelOrder(IdReqVo reqVo) throws DepotNextDoorException ;

    void systemCancelOrder(Long orderId) throws DepotNextDoorException;

    OrderSettlePageRespVo getSettleResult(OrderSettlePageReqVo reqVo) throws DepotNextDoorException;

    PaymentRespVo createPrePayOrder(OrderCreateReqVo reqVo) throws DepotNextDoorException;

    Order getOrder(Long id);

    Order saveOrder(Order order);

    void applyReturn(OrderApplyReturnReqVo reqVo);

    void lockStock(Order order) throws DepotNextDoorException;
}
