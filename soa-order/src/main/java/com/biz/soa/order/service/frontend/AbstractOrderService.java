package com.biz.soa.order.service.frontend;

import com.biz.core.asserts.SystemAsserts;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.repository.order.OrderRepository;
import com.biz.gbck.dao.mysql.repository.order.OrderReturnRepository;
import com.biz.gbck.dao.redis.repository.order.OrderRedisDao;
import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.transform.order.Order2OrderRo;
import com.biz.service.AbstractBaseService;
import com.biz.service.SequenceService;
import com.biz.service.cart.ShopCartService;
import com.biz.service.stock.StockService;
import com.biz.soa.feign.client.org.ShopFeignClient;
import com.biz.soa.feign.client.org.UserFeignClient;
import com.biz.soa.order.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AbstractOrderService
 *
 * @author lei
 * @date 2017年05月15日
 * @reviewer
 * @see
 */
public abstract class AbstractOrderService extends AbstractBaseService {

    @Autowired
    protected OrderRedisDao orderRedisDao;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected OrderReturnRepository orderReturnRepository;

    @Autowired
    protected SequenceService sequenceService;

    @Autowired
    protected PaymentService paymentService;

    @Autowired(required = false)
    protected StockService stockService;

    @Autowired
    protected ShopCartService shopCartService;

    @Autowired(required = false)
    protected UserFeignClient userFeignClient;

    @Autowired(required = false)
    protected ShopFeignClient shopFeignClient;

    protected Order getOrder(Long id) {
        return orderRepository.findOne(id);
    }

    protected void saveOrder(Order order) {
        orderRepository.save(order);
    }

    protected Order updateOrderStatus(Order order, OrderStatus newStatus) {
        logger.debug("修改订单状态 orderId={}. {} --> {}", order.getStatus(), newStatus);
        SystemAsserts.notNull(newStatus, "新订单状态不能为空");
        order.setStatus(newStatus);

        preCommitOpt(() -> saveOrUpdateUsingPo(orderRepository, orderRedisDao, order, new Order2OrderRo()));

        return order;
    }


}