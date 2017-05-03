package com.biz.rest.controller.order;

import com.biz.gbck.vo.order.OrderListReqVo;
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
    public JSONResult allTypeOrder(HttpServletRequest request){
        OrderListReqVo reqVo = super.parseBizData(request, OrderListReqVo.class);
        orderService.listOrders(reqVo);
        return new JSONResult();
    }



}
