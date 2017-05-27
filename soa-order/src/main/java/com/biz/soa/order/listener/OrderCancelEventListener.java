package com.biz.soa.order.listener;

import com.biz.core.event.AbstractBizEventListener;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.transform.order.OrderItem2StockItemVO;
import com.biz.gbck.vo.order.event.OrderCancelEvent;
import com.biz.gbck.vo.order.event.OrderCreateEvent;
import com.biz.gbck.vo.stock.StockItemVO;
import com.biz.gbck.vo.stock.UpdateCompanyLockStockReqVO;
import com.biz.service.order.frontend.OrderFrontendService;
import com.biz.soa.feign.client.stock.StockFeignClient;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 订单取消完成事件监听
 *
 * @author lei
 * @date 2017年05月15日
 * @reviewer
 * @see
 */
@Component
public class OrderCancelEventListener extends AbstractBizEventListener<OrderCancelEvent> {

    @Autowired
    private OrderFrontendService orderFrontendService;

    @Autowired(required = false)
    private StockFeignClient stockFeignClient;


    @Override
    protected void handleEvent(OrderCancelEvent event) {
        if (logger.isDebugEnabled()) {
            logger.debug("订单取消事件-------请求:{}", event);
        }
        Long orderId = event.getOrderId();
        Order order = orderFrontendService.getOrder(orderId);
        if (order != null) {
            this.releaseOrderLockStock(order);
        } else {
            logger.warn("订单取消事件-------为查询到订单. orderId: {}", orderId);
        }

    }

    //释放锁定库存
    private void releaseOrderLockStock(Order order) {
        String orderCode = order.getOrderCode();
        Long userId = order.getUserId();

        List<StockItemVO> items = Lists.transform(order.getItems(), new OrderItem2StockItemVO(true));
        UpdateCompanyLockStockReqVO releaseLockReqVo = new UpdateCompanyLockStockReqVO(orderCode, userId, items);
        if (logger.isDebugEnabled()) {
            logger.debug("订单取消事件-------释放锁定库存vo:{}", releaseLockReqVo);
        }
        try {
            stockFeignClient.orderUpdateLockStocks(newArrayList(releaseLockReqVo));
        } catch (Exception e) {
            logger.error("订单取消事件-------订单[orderId={}, orderCode={}]释放锁定库存失败", order.getId(), orderCode, e);
        }
    }
}