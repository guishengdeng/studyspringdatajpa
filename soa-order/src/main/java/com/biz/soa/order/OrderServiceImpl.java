package com.biz.soa.order;

import com.biz.gbck.vo.order.OrderListReqVo;
import com.biz.gbck.vo.order.OrderRespVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.order.OrderService;

import java.util.List;

/**
 * 订单service
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
public class OrderServiceImpl extends AbstractBaseService implements OrderService {


    @Override
    public List<OrderRespVo> listOrders(OrderListReqVo reqVo) {
        return null;
    }
}
