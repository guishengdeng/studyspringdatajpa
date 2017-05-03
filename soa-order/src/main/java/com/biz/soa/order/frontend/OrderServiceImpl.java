package com.biz.soa.order.frontend;

import com.biz.gbck.vo.order.OrderListReqVo;
import com.biz.gbck.vo.order.OrderRespVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.order.frontend.OrderFrontendService;

import java.util.List;

/**
 * 订单service
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
public class OrderServiceImpl extends AbstractBaseService implements OrderFrontendService {


    @Override
    public List<OrderRespVo> listOrders(OrderListReqVo reqVo) {
        return null;
    }
}
