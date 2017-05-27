package com.biz.rest.controller.order;

import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.order.req.*;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.util.RestUtil;
import com.biz.service.order.frontend.OrderFrontendService;
import com.biz.soa.feign.client.order.OrderFeignClient;
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

    @Autowired(required = false)
    private OrderFeignClient orderFeignClient;

    //订单列表
    @RequestMapping("/list")
    public JSONResult allTypeOrders(HttpServletRequest request){
        OrderListReqVo reqVo = RestUtil.parseBizData(request, OrderListReqVo.class);
        return RestUtil.parseBizResult(orderFeignClient.allTypeOrders(reqVo));
    }

    //订单详情
    @RequestMapping("/detail")
    public JSONResult orderDetail(HttpServletRequest request) throws DepotNextDoorException {
        IdReqVo reqVo = RestUtil.parseBizData(request, IdReqVo.class);
        return RestUtil.parseBizResult(orderFeignClient.orderDetail(reqVo));
    }

    //取消订单
    @RequestMapping("/cancel")
    public JSONResult cancelOrder(HttpServletRequest request) {
        IdReqVo reqVo = RestUtil.parseBizData(request, IdReqVo.class);
        return RestUtil.parseBizResult(orderFeignClient.cancelOrder(reqVo));
    }

    //结算
    @RequestMapping("/settle")
    public JSONResult settle(HttpServletRequest request) throws DepotNextDoorException {
        OrderSettlePageReqVo reqVo = RestUtil.parseBizData(request, OrderSettlePageReqVo.class);
        return RestUtil.parseBizResult(orderFeignClient.settle(reqVo));
    }

    //货到付款结算
    @RequestMapping("/createOrderNoPay")
    public JSONResult createOrderNoPay(HttpServletRequest request) throws DepotNextDoorException {
        OrderCreateReqVo reqVo = RestUtil.parseBizData(request, OrderCreateReqVo.class);
        reqVo.setPaymentType(PaymentType.PAY_ON_DELIVERY.getValue());
        return RestUtil.parseBizResult(orderFeignClient.createOrderNoPay(reqVo));
    }

    //支付宝结算
    @RequestMapping("/createOrderAlipay")
    public JSONResult createOrderAlipay(HttpServletRequest request) throws DepotNextDoorException {
        OrderCreateReqVo reqVo = RestUtil.parseBizData(request, OrderCreateReqVo.class);
        reqVo.setPaymentType(PaymentType.ALIPAY.getValue());
        return RestUtil.parseBizResult(orderFeignClient.createOrderNoPay(reqVo));
    }

    //微信结算
    @RequestMapping("/createOrderWechat")
    public JSONResult createOrderWechat(HttpServletRequest request) throws DepotNextDoorException {
        OrderCreateWechatReqVo reqVo = RestUtil.parseBizData(request, OrderCreateWechatReqVo.class);
        reqVo.setPaymentType(PaymentType.WECHAT.getValue());
        return RestUtil.parseBizResult(orderFeignClient.createOrderNoPay(reqVo));
    }

    //申请退货
    @RequestMapping("/applyReturn")
    public JSONResult applyReturn(HttpServletRequest request) {
        OrderApplyReturnReqVo reqVo = RestUtil.parseBizData(request, OrderApplyReturnReqVo.class);
        return RestUtil.parseBizResult(orderFeignClient.applyReturn(reqVo));
    }


}
