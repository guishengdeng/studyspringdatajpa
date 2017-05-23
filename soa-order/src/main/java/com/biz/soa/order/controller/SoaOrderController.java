package com.biz.soa.order.controller;

import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.PageRespVo;
import com.biz.gbck.vo.order.req.*;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;
import com.biz.service.order.frontend.OrderFrontendService;
import com.biz.soa.base.SoaBaseController;
import com.biz.support.web.handler.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单soa controller
 *
 * @author lei
 * @date 2017年05月14日
 * @reviewer
 * @see
 */
@RestController
@RequestMapping(value = "/soa/order")
public class SoaOrderController extends SoaBaseController {

    @Autowired
    private OrderFrontendService orderService;

    //订单列表
    @RequestMapping("/app/list")
    public JSONResult allTypeOrders(@RequestBody OrderListReqVo reqVo) throws DepotNextDoorException {
        PageRespVo pageRespVo = orderService.listOrders(reqVo);
        return new JSONResult(pageRespVo);
    }

    //订单详情
    @RequestMapping("/app/detail")
    public JSONResult orderDetail(@RequestBody IdReqVo reqVo) throws DepotNextDoorException {
        OrderRespVo orderRespVo = orderService.getOrderDetail(reqVo);
        return new JSONResult(orderRespVo);
    }

    //取消订单
    @RequestMapping("/app/cancel")
    public JSONResult cancelOrder(@RequestBody IdReqVo reqVo) throws DepotNextDoorException {
        orderService.cancelOrder(reqVo);
        return new JSONResult();
    }

    //结算
    @RequestMapping("/app/settle")
    public JSONResult settle(@RequestBody OrderSettlePageReqVo reqVo) throws DepotNextDoorException {
        OrderSettlePageRespVo respVo = orderService.getSettleResult(reqVo);
        return new JSONResult(respVo);
    }

    //货到付款结算
    @RequestMapping("/app/createOrderNoPay")
    public JSONResult createOrderNoPay(@RequestBody OrderCreateReqVo reqVo) throws DepotNextDoorException {
        reqVo.setPaymentType(PaymentType.PAY_ON_DELIVERY.getValue());
        PaymentRespVo respVo = orderService.createPrePayOrder(reqVo);
        return new JSONResult(respVo);
    }

    //支付宝结算
    @RequestMapping("/app/createOrderAlipay")
    public JSONResult createOrderAlipay(@RequestBody OrderCreateReqVo reqVo) throws DepotNextDoorException {
        reqVo.setPaymentType(PaymentType.ALIPAY.getValue());
        PaymentRespVo respVo = orderService.createPrePayOrder(reqVo);
        return new JSONResult(respVo);
    }

    //微信结算
    @RequestMapping("/app/createOrderWechat")
    public JSONResult createOrderWechat(@RequestBody OrderCreateWechatReqVo reqVo) throws DepotNextDoorException {
        reqVo.setPaymentType(PaymentType.WECHAT.getValue());
        PaymentRespVo respVo = orderService.createPrePayOrder(reqVo);
        return new JSONResult(respVo);
    }

    //申请退货
    @RequestMapping("/app/applyReturn")
    public JSONResult applyReturn(@RequestBody OrderApplyReturnReqVo reqVo) {
        orderService.applyReturn(reqVo);
        return new JSONResult();
    }

    @GetMapping(value = "/app/test")
    public String getTestString() {
        return "I am a test String";
    }

}
