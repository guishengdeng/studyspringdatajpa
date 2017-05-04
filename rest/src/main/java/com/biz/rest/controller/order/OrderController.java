package com.biz.rest.controller.order;

import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.order.req.OrderCreateReqVo;
import com.biz.gbck.vo.order.req.OrderListReqVo;
import com.biz.gbck.vo.order.req.OrderSettlePageReqVo;
import com.biz.gbck.vo.order.resp.OrderCreateRespVo;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.rest.controller.BaseRestController;
import com.biz.service.order.frontend.OrderFrontendService;
import com.biz.support.web.handler.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单controller
 *
 * @author lei
 * @date 2017年04月25日
 * @reviewer
 * @see
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseRestController {

    @Autowired
    private OrderFrontendService orderService;

    //订单列表
    @RequestMapping("/list")
    public JSONResult allTypeOrders(HttpServletRequest request){
        OrderListReqVo reqVo = super.parseBizData(request, OrderListReqVo.class);
        orderService.listOrders(reqVo);
        return new JSONResult();
    }

    //订单详情
    @RequestMapping("/detail")
    public JSONResult orderDetail(HttpServletRequest request){
        IdReqVo reqVo = super.parseBizData(request, IdReqVo.class);
        OrderRespVo orderRespVo = orderService.getOrderDetail(reqVo);
        return new JSONResult(orderRespVo);
    }

    //取消订单
    @RequestMapping("/cancel")
    public JSONResult cancelOrder(HttpServletRequest request) {
        IdReqVo reqVo = super.parseBizData(request, IdReqVo.class);
        orderService.cancelOrder(reqVo);
        return new JSONResult();
    }

    //结算
    @RequestMapping("/settle")
    public JSONResult createOrder(HttpServletRequest request) {
        OrderSettlePageReqVo reqVo = super.parseBizData(request, OrderSettlePageReqVo.class);
        OrderSettlePageRespVo respVo = orderService.settle(reqVo);
        return new JSONResult(respVo);
    }

    //货到付款结算
    @RequestMapping("/confirmNoPay")
    public JSONResult comfirmNoPay(HttpServletRequest request) {
        OrderCreateReqVo reqVo = super.parseBizData(request, OrderCreateReqVo.class);
        reqVo.setPaymentType(PaymentType.PAY_ON_DELIVERY.getValue());
        OrderCreateRespVo respVo = orderService.createOrder(reqVo);
        return new JSONResult(respVo);
    }



}
