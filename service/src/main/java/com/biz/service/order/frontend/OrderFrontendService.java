package com.biz.service.order.frontend;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.order.req.OrderCreateReqVo;
import com.biz.gbck.vo.order.req.OrderCreateWechatReqVo;
import com.biz.gbck.vo.order.req.OrderListReqVo;
import com.biz.gbck.vo.order.req.OrderSettlePageReqVo;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;

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

    PaymentRespVo confirmOrder(OrderCreateReqVo reqVo);

    OrderSettlePageRespVo settle(OrderSettlePageReqVo reqVo);

    PaymentRespVo confirmWechatOrder(OrderCreateWechatReqVo reqVo) throws DepotNextDoorException;

    PaymentRespVo confirmAlipayOrder(OrderCreateReqVo reqVo) throws DepotNextDoorException ;

    Order getOrder(Long id);

    void saveOrder(Order order);
}
