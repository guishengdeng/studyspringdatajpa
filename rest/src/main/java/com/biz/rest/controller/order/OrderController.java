package com.biz.rest.controller.order;

import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.PageRespVo;
import com.biz.gbck.vo.order.req.*;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;
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
        PageRespVo pageRespVo = orderService.listOrders(reqVo);
        return new JSONResult(pageRespVo);
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
    public JSONResult settle(HttpServletRequest request) {
        OrderSettlePageReqVo reqVo = super.parseBizData(request, OrderSettlePageReqVo.class);
        OrderSettlePageRespVo respVo = orderService.settle(reqVo);
        return new JSONResult(respVo);
    }

    //货到付款结算
    @RequestMapping("/createOrderNoPay")
    public JSONResult createOrderNoPay(HttpServletRequest request) throws DepotNextDoorException {
        OrderCreateReqVo reqVo = super.parseBizData(request, OrderCreateReqVo.class);
        reqVo.setPaymentType(PaymentType.PAY_ON_DELIVERY.getValue());
        PaymentRespVo respVo = orderService.createPrePayOrder(reqVo);
        return new JSONResult(respVo);
    }

    //支付宝结算
    @RequestMapping("/createOrderAlipay")
    public JSONResult createOrderAlipay(HttpServletRequest request) throws DepotNextDoorException {
        OrderCreateReqVo reqVo = super.parseBizData(request, OrderCreateReqVo.class);
        reqVo.setPaymentType(PaymentType.ALIPAY.getValue());
        PaymentRespVo respVo = orderService.createPrePayOrder(reqVo);
        return new JSONResult(respVo);
    }

    //微信结算
    @RequestMapping("/createOrderWechat")
    public JSONResult createOrderWechat(HttpServletRequest request) throws DepotNextDoorException {
        OrderCreateWechatReqVo reqVo = super.parseBizData(request, OrderCreateWechatReqVo.class);
        reqVo.setPaymentType(PaymentType.WECHAT.getValue());
        PaymentRespVo respVo = orderService.createPrePayOrder(reqVo);
        return new JSONResult(respVo);
    }

    //申请退货
    @RequestMapping("/applyReturn")
    public JSONResult applyReturn(HttpServletRequest request){
        OrderApplyReturnReqVo reqVo = super.parseBizData(request, OrderApplyReturnReqVo.class);
        orderService.applyReturn(reqVo);
        return new JSONResult();
    }





}
