package com.biz.soa.order.service.frontend;

import com.biz.core.asserts.SystemAsserts;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.repository.order.OrderRepository;
import com.biz.gbck.dao.redis.repository.order.OrderRedisDao;
import com.biz.gbck.enums.order.OrderShowStatus;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.order.OrderListReqVo;
import com.biz.gbck.vo.order.OrderRespVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.order.frontend.OrderFrontendService;
import com.biz.soa.builder.OrderRespVoBuilder;
import org.codelogger.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 订单service
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
@Service
public class OrderFrontendServiceImpl extends AbstractBaseService implements OrderFrontendService {

    @Autowired
    private OrderRedisDao orderRedisDao;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderRespVo> listOrders(OrderListReqVo reqVo) {
        SystemAsserts.notNull(reqVo);
        List<Long> orderIds = orderRedisDao.findOrderIdsByUserIdWithPeriod(Long.valueOf(reqVo
                .getUserId()), OrderShowStatus.valueOf(reqVo.getStatus()), reqVo.getPage(), reqVo.getSize());
        List<Order> orders = orderRepository.findAll(orderIds);
        return this.buildOrderVos(orders);
    }

    @Override
    public OrderRespVo getOrderDetail(IdReqVo reqVo) {
        SystemAsserts.notNull(reqVo);
        Order order = orderRepository.findOne(reqVo.getId());
        return CollectionUtils.getFirstNotNullValue(this.buildOrderVos(newArrayList(order)));
    }

    private List<OrderRespVo> buildOrderVos(List<Order> orders) {
        List<OrderRespVo> orderRespVos = newArrayList();
        for (Order order : orders) {
            OrderRespVo respVo = OrderRespVoBuilder.createBuider(order).setSellerInfo(null).setBuyerInfo(null, null,
                    null).setItems(order.getItems()).build();
            orderRespVos.add(respVo);
        }
        return orderRespVos;
    }
}
