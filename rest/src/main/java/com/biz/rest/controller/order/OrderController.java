package com.biz.rest.controller.order;

import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.order.OrderCreateReqVo;
import com.biz.gbck.vo.order.OrderCreateRespVo;
import com.biz.gbck.vo.order.OrderListReqVo;
import com.biz.gbck.vo.order.OrderRespVo;
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

    @RequestMapping("/createOrderNoPay")
    public JSONResult createOrderNoPay(HttpServletRequest request) {
        OrderCreateReqVo reqVo = super.parseBizData(request, OrderCreateReqVo.class);
        reqVo.setPaymentType(PaymentType.PAY_ON_DELIVERY.getValue());
        OrderCreateRespVo respVo = orderService.createOrder(reqVo);
        return new JSONResult(respVo);
    }



}
