package com.biz.soa.order.service.backend;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.vo.order.req.OrderQueryReqVo;
import com.biz.gbck.vo.order.resp.OrderResponseVo;
import com.biz.service.order.backend.OrderBackendService;
import com.biz.soa.order.service.frontend.AbstractOrderService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单接口实现(后台)
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
@Service
public class OrderBackendServiceImpl extends AbstractOrderService implements OrderBackendService {

    @Override
    public List<Order> listOrder() {
        return null;
    }

    @Override
    public Page<Order> queryOrdersByCondition(OrderQueryReqVo vo) {
        return null;
    }

    @Override
    public Page<OrderResponseVo> pageOrder2PageRespVo(Page<Order> page) {
        return null;
    }
}
