package com.biz.soa.order.service.frontend;

import com.biz.core.asserts.SystemAsserts;
import com.biz.core.util.Timers;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.order.OrderItem;
import com.biz.gbck.dao.mysql.repository.order.OrderRepository;
import com.biz.gbck.dao.redis.repository.order.OrderRedisDao;
import com.biz.gbck.enums.order.OrderShowStatus;
import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.order.PaymentException;
import com.biz.gbck.transform.order.Order2OrderRo;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.PageRespVo;
import com.biz.gbck.vo.cart.ShopCartItemRespVo;
import com.biz.gbck.vo.cart.ShopCartListReqVo;
import com.biz.gbck.vo.cart.ShopCartRespVo;
import com.biz.gbck.vo.order.event.UserOrderCancelEvent;
import com.biz.gbck.vo.order.req.*;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;
import com.biz.gbck.vo.stock.UpdatePartnerLockStockReqVO;
import com.biz.service.AbstractBaseService;
import com.biz.service.SequenceService;
import com.biz.service.cart.ShopCartService;
import com.biz.service.order.frontend.OrderFrontendService;
import com.biz.service.stock.StockService;
import com.biz.soa.builder.OrderBuilder;
import com.biz.soa.builder.OrderRespVoBuilder;
import com.biz.soa.builder.OrderSettlePageRespVoBuilder;
import com.biz.soa.order.service.payment.PaymentService;
import org.codelogger.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private StockService stockService;

    @Autowired
    private ShopCartService shopCartService;

    /*****************public begin*********************/

    @Override
    public PageRespVo listOrders(OrderListReqVo reqVo) {
        SystemAsserts.notNull(reqVo);
        OrderShowStatus status = OrderShowStatus.valueOf(reqVo.getStatus());
        SystemAsserts.notNull("status", "订单状态不合法");
        Long userId = Long.valueOf(reqVo.getUserId());
        List<Long> orderIds = orderRedisDao.findOrderIdsByUserIdWithPeriod(userId, status,
                reqVo.getPage(), reqVo.getSize());
        List<Order> orders = orderRepository.findAll(orderIds);
        List<OrderRespVo> orderRespVos = this.buildOrderVos(userId, orders);

        return new PageRespVo(reqVo.getPage(), orderRespVos);
    }

    @Override
    public OrderRespVo getOrderDetail(IdReqVo reqVo) {
        SystemAsserts.notNull(reqVo);
        Order order = orderRepository.findOne(reqVo.getId());
        return CollectionUtils.getFirstNotNullValue(this.buildOrderVos(Long.valueOf(reqVo.getUserId()), newArrayList
                (order)));
    }

    @Transactional
    @Override
    public void cancelOrder(IdReqVo reqVo) {
        Order order = orderRepository.findOne(reqVo.getId());
        SystemAsserts.notNull(order, "订单不存在");
        if (order.isCancelable(false)) {
            if (logger.isDebugEnabled()) {
                order = this.updateOrderStatus(order, OrderStatus.CANCELED);
                super.publishEventUsingTx(new UserOrderCancelEvent(this, order.getId()));
            }
        }

    }


    @Override
    public OrderSettlePageRespVo settle(OrderSettlePageReqVo reqVo) {
        //TODO 各种计算
        return OrderSettlePageRespVoBuilder.createBuilder().setItems(null).setFreight(null)
                .setCoupons(null).setPayLimitTime(null).setPaymentTyps(null).setPromtions(null).setBuyerInfo(null,
                        null, null).build();
    }

    /**
     * 创建订单
     */
    @Transactional
    @Override
    public PaymentRespVo createPrePayOrder(OrderCreateReqVo reqVo) throws DepotNextDoorException {
        Order order = this.createOrder(reqVo);
        if (PaymentType.PAY_ON_DELIVERY.getValue() == reqVo.getPaymentType()) {
            return paymentService.noNeedPay(order);
        } else if (PaymentType.ALIPAY.getValue() == reqVo.getPaymentType()) {
            return paymentService.getAlipaySign(order);
        } else if (PaymentType.WECHAT.getValue() == reqVo.getPaymentType()) {
            return paymentService.wechatPay((OrderCreateWechatReqVo)reqVo, order);
        }

        throw new PaymentException("无效的支付方式");
    }

    @Override
    public void applyReturn(OrderApplyReturnReqVo reqVo) {

    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }



    /*****************public end*********************/


    private List<OrderRespVo> buildOrderVos(Long userId, List<Order> orders) {
        List<OrderRespVo> orderRespVos = newArrayList();
        //TODO 根据用户id获取用户信息
        for (Order order : orders) {
            OrderRespVo respVo = OrderRespVoBuilder.createBuilder(order).setBuyerInfo(null, null, null).setItems(order.getItems()).build();
            orderRespVos.add(respVo);
        }
        return orderRespVos;
    }

    /**
     * 创建订单
     */
    private Order createOrder(OrderCreateReqVo reqVo) throws DepotNextDoorException {
        Timers timers = Timers.createAndBegin(logger.isDebugEnabled());
        long id = idService.nextId();
        String orderCode = sequenceService.generateOrderCode();
        ShopCartListReqVo cartInfoReqVo = new ShopCartListReqVo();
        cartInfoReqVo.setUserId(reqVo.getUserId());
        ShopCartRespVo cartVo = shopCartService.getCartInfo(cartInfoReqVo);
        SystemAsserts.notNull(cartVo, "未获取到进货单信息");
        List<ShopCartItemRespVo> items = cartVo.getSelectedItems();
        SystemAsserts.notEmpty(items, "未获取到进货单信息");

        List<OrderItem> orderItems = this.transOrderItems(items);
        Order order = OrderBuilder.createBuilder(reqVo)
                .setItems(orderItems)
                .setFreeAmount(cartVo.getFreeAmount())
                .setVoucherAmount(cartVo.getVoucherAmount())
                .setPayAmount(cartVo.getPayAmount())
                .setExpireTime(null) //TODO
                .setPaymentType(null) //TODO
                .build(id, orderCode);

        this.lockStock(order);
        timers.print("创建订单用时");
        return order;
    }

    //锁定库存
    private void lockStock(Order order) throws DepotNextDoorException {
        List<UpdatePartnerLockStockReqVO> lockStockReqVOS = newArrayList();
        for (OrderItem orderItem : order.getItems()) {
            UpdatePartnerLockStockReqVO lockStockReqVO = new UpdatePartnerLockStockReqVO(order.getOrderCode(),
                    orderItem.getProductId(), order.getSellerId(), orderItem.getQuantity());
            lockStockReqVOS.add(lockStockReqVO);
        }
        try {
            stockService.updateLockStocks(lockStockReqVOS);
        } catch (Exception e) {
            logger.error("锁定库存出错", e);
            throw e;
        }
    }

    private Order updateOrderStatus(Order order, OrderStatus newStatus) {
        logger.debug("修改订单状态 orderId={}. {} --> {}", order.getStatus(), newStatus);
        SystemAsserts.notNull(newStatus, "新订单状态不能为空");
        order.setStatus(newStatus);

        preCommitOpt(() -> saveOrUpdateUsingPo(orderRepository, orderRedisDao, order, new Order2OrderRo()));

        return order;
    }

    private List<OrderItem> transOrderItems(List<ShopCartItemRespVo> items) {
        List<OrderItem> orderItems = newArrayList();
        for (ShopCartItemRespVo item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(idService.nextId());
            orderItem.setProductId(item.getProductId());
            orderItem.setProductCode(item.getProductCode());
            orderItem.setName(item.getName());
            orderItem.setLogo(item.getLogo());
            orderItem.setPrice(item.getPrice());
            orderItem.setMarketPrice(item.getMarketPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItems.add(orderItem);
        }

        return orderItems;
    }


}
