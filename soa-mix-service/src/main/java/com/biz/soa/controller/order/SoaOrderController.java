/*
package com.biz.soa.controller.order;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.vo.order.req.OrderQueryReqVo;
import com.biz.gbck.vo.order.resp.OrderResponseVo;
import com.biz.service.order.backend.OrderBackendService;
import com.biz.soa.base.SoaBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

*/
/**
 * SoaOrderController
 *
 * @author guisheng.deng
 * @date 2017年05月18日
 * @reviewer
 * @description
 * @see
 *//*

@RestController
@RequestMapping(value="soa/orderService")
public class SoaOrderController extends SoaBaseController {

    @Autowired
    private OrderBackendService orderBackendService;

    @RequestMapping(value = "list")
    List<OrderResponseVo> orderList(@RequestBody OrderQueryReqVo vo){
        Page<Order> pageList = orderBackendService.queryOrdersByCondition(vo);
        return orderBackendService.pageOrder2ListRespVo(pageList);
    }
}*/
