package com.biz.soa.order.controller;

import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.PageRespVo;
import com.biz.gbck.vo.order.req.*;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.service.order.frontend.OrderFrontendService;
import com.biz.soa.base.SoaBaseController;
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
    public MicroServiceResult<PageRespVo> allTypeOrders(@RequestBody OrderListReqVo reqVo) {
        try {
            return render200(orderService.listOrders(reqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }

    //订单详情
    @RequestMapping("/app/detail")
    public MicroServiceResult<OrderRespVo> orderDetail(@RequestBody IdReqVo reqVo){
        try {
            return render200(orderService.getOrderDetail(reqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }

    //取消订单
    @RequestMapping("/app/cancel")
    public MicroServiceResult cancelOrder(@RequestBody IdReqVo reqVo) {
        try {
            orderService.cancelOrder(reqVo);
            return render200(null);
        } catch (Exception e) {
            return render500(e);
        }
    }

    //结算
    @RequestMapping("/app/settle")
    public MicroServiceResult<OrderSettlePageRespVo> settle(@RequestBody OrderSettlePageReqVo reqVo) {
        try {
            return render200(orderService.getSettleResult(reqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }

    //货到付款结算
    @RequestMapping("/app/createOrderNoPay")
    public MicroServiceResult<PaymentRespVo> createOrderNoPay(@RequestBody OrderCreateReqVo reqVo) {
        try {
            reqVo.setPaymentType(PaymentType.PAY_ON_DELIVERY.getValue());
            return render200(orderService.createPrePayOrder(reqVo));
        } catch (Exception e) {
            return render500(e);
        }

    }

    //支付宝结算
    @RequestMapping("/app/createOrderAlipay")
    public MicroServiceResult<PaymentRespVo> createOrderAlipay(@RequestBody OrderCreateReqVo reqVo) {
        try {
            reqVo.setPaymentType(PaymentType.ALIPAY.getValue());
            return render200(orderService.createPrePayOrder(reqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }

    //微信结算
    @RequestMapping("/app/createOrderWechat")
    public MicroServiceResult<PaymentRespVo> createOrderWechat(@RequestBody OrderCreateWechatReqVo reqVo) {
        try {
            reqVo.setPaymentType(PaymentType.WECHAT.getValue());
            return render200(orderService.createPrePayOrder(reqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }

    //申请退货
    @RequestMapping("/app/applyReturn")
    public MicroServiceResult applyReturn(@RequestBody OrderApplyReturnReqVo reqVo) {
        try {
            orderService.applyReturn(reqVo);
            return render200(null);
        } catch (Exception e) {
            return render500(e);
        }
    }

    @GetMapping(value = "/app/test")
    public MicroServiceResult<String> getTestString() {
        return render200("I am a test String");
    }

}
