package com.biz.service.order.backend;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.vo.order.req.OrderQueryReqVo;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderResponseVo;
import com.biz.service.org.interfaces.PlatformService;
import com.biz.service.partner.interfaces.PartnerService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 订单接口(后台)
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
public interface OrderBackendService {

    List<Order>  listOrder();

    Page<Order> queryOrdersByCondition(OrderQueryReqVo vo);

    /**
     * 将Page<Order> 转化成Page<OrderResponseVo>
     */
    Page<OrderResponseVo> pageOrder2PageRespVo(Page<Order> page);
}
