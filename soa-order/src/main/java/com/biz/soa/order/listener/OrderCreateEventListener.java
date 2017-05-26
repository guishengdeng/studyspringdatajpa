package com.biz.soa.order.listener;

import com.biz.core.event.AbstractBizEventListener;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.vo.order.event.OrderCreateEvent;
import com.biz.service.cart.ShopCartService;
import com.biz.service.order.frontend.OrderFrontendService;
import com.biz.soa.feign.client.stock.StockFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单创建完成事件监听
 *
 * @author lei
 * @date 2017年05月15日
 * @reviewer
 * @see
 */
@Component
public class OrderCreateEventListener extends AbstractBizEventListener<OrderCreateEvent> {

    @Autowired
    private OrderFrontendService orderFrontendService;

    @Autowired
    private ShopCartService shopCartService;

    @Autowired(required = false)
    private StockFeignClient stockFeignClient;


    @Override
    protected void handleEvent(OrderCreateEvent event) {
        if (logger.isDebugEnabled()) {
            logger.debug("订单创建事件-------请求:{}", event);
        }
        Long orderId = event.getOrderId();
        Order order = orderFrontendService.getOrder(orderId);
        if (order != null) {
            try {
                orderFrontendService.lockStock(order);
            } catch (Exception e) {
                logger.debug("锁定库存失败", e);
            }
            logger.info("创建订单完成, 清空购物车. orderId: {},  userId: {}", orderId, order.getUserId());
            shopCartService.cleanCart(String.valueOf(order.getUserId()));
        } else {
            logger.warn("订单创建事件-------为查询到订单. orderId: {}", orderId);
        }

    }
}
