package com.biz.soa.order.service.frontend;

import com.biz.core.asserts.BusinessAsserts;
import com.biz.core.asserts.SystemAsserts;
import com.biz.core.util.Timers;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.order.OrderItem;
import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.enums.order.OrderShowStatus;
import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;
import com.biz.gbck.exceptions.order.PaymentException;
import com.biz.gbck.transform.order.OrderItem2StockItemVO;
import com.biz.gbck.transform.order.ShopCartItemRespVo2OrderItemRespVo;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.PageRespVo;
import com.biz.gbck.vo.cart.ShopCartListSettleReqVo;
import com.biz.gbck.vo.cart.ShopCartRespVo;
import com.biz.gbck.vo.order.event.SystemOrderCancelEvent;
import com.biz.gbck.vo.order.event.UserOrderCancelEvent;
import com.biz.gbck.vo.order.req.*;
import com.biz.gbck.vo.order.resp.*;
import com.biz.gbck.vo.org.UserInfoVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;
import com.biz.gbck.vo.product.promotion.OrderActivePromotionItemVO;
import com.biz.gbck.vo.product.promotion.OrderPromotionRespVO;
import com.biz.gbck.vo.stock.StockItemVO;
import com.biz.gbck.vo.stock.UpdateCompanyLockStockReqVO;
import com.biz.service.order.frontend.OrderFrontendService;
import com.biz.soa.order.builder.OrderBuilder;
import com.biz.soa.order.builder.OrderRespVoBuilder;
import com.biz.soa.order.builder.OrderReturnBuilder;
import com.biz.soa.order.builder.OrderSettlePageRespVoBuilder;
import com.biz.soa.order.util.OrderUtil;
import com.google.common.collect.Lists;
import org.codelogger.utils.CollectionUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class OrderFrontendServiceImpl extends AbstractOrderService implements OrderFrontendService {

    /*****************public begin*********************/

    @Override
    public PageRespVo listOrders(OrderListReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("获取订单列表-------请求vo: {}", reqVo);
        }
        SystemAsserts.notNull(reqVo);
        OrderShowStatus status = OrderShowStatus.valueOf(reqVo.getStatus());
        SystemAsserts.notNull(status, "订单状态不合法");
        Long userId = Long.valueOf(reqVo.getUserId());
        List<Long> orderIds = orderRedisDao.findOrderIdsByUserIdWithPeriod(userId, status, reqVo.getLastFlag(), reqVo
                .getSize());
        List<Order> orders = orderRepository.findAll(orderIds);
        List<OrderRespVo> orderRespVos = this.buildOrderVos(orders);

        Long lastOrderId = CollectionUtils.getLastElement(orderIds);
        PageRespVo pageRespVo = new PageRespVo(lastOrderId != null ? lastOrderId.toString() : null, orderRespVos);
        if (logger.isDebugEnabled()) {
            logger.debug("获取订单列表-------请求: {}, 返回值: {}", reqVo, pageRespVo);
        }
        return pageRespVo;
    }

    @Override
    public OrderRespVo getOrderDetail(IdReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("获取订单详情-------请求vo: {}", reqVo);
        }
        SystemAsserts.notNull(reqVo);
        Order order = orderRepository.findOne(reqVo.getId());
        OrderRespVo orderRespVo = CollectionUtils.getFirstNotNullValue(this.buildOrderVos(newArrayList(order)));
        if (logger.isDebugEnabled()) {
            logger.debug("获取订单详情-------请求: {}, 返回值: {}", reqVo, orderRespVo);
        }
        return orderRespVo;
    }

    @Transactional
    @Override
    public void cancelOrder(IdReqVo reqVo) throws DepotNextDoorException  {
        if (logger.isDebugEnabled()) {
            logger.debug("取消订单-------请求vo: {}", reqVo);
        }
        super.queryPayStatus(reqVo.getId());
        Order order = orderRepository.findOne(reqVo.getId());
        super.validUser(order, reqVo);
        SystemAsserts.notNull(order, "订单不存在");
        SystemAsserts.isTrue(order.isCancelable(false), "订单状态已经发生变化，不能取消");
        order = super.updateOrderStatus(order, OrderStatus.CANCELED);
        super.publishEventUsingTx(new UserOrderCancelEvent(this, order.getId()));

    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void systemCancelOrder(Long orderId) throws DepotNextDoorException  {
        logger.info("系统取消订单-------请求vo: {}", orderId);
        super.queryPayStatus(orderId);
        Order order = orderRepository.findOne(orderId);
        SystemAsserts.notNull(order, "订单不存在");
        if (order.isPayTimeout()) {
            order = super.updateOrderStatus(order, OrderStatus.CANCELED);
            logger.info("系统取消订单[orderId={}]成功", order.getId());
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("系统不能取消订单[orderId={}]", order.getId());
            }
        }
        super.publishEventUsingTx(new SystemOrderCancelEvent(this, order.getId()));

    }


    @Override
    public OrderSettlePageRespVo getSettleResult(OrderSettlePageReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("订单结算-------请求vo: {}. 创建订单: {}", reqVo, reqVo instanceof OrderCreateReqVo);
        }
        String userId = reqVo.getUserId();
        UserInfoVo userInfo = userFeignClient.findUserInfo(Long.valueOf(userId));
        BusinessAsserts.notNull(userInfo, DepotNextDoorExceptions.User.USER_NOT_EXIST);

        ShopCartListSettleReqVo cartSettleReqVo = new ShopCartListSettleReqVo();
        BeanUtils.copyProperties(reqVo, cartSettleReqVo);

        ShopCartRespVo cartInfo = shopCartService.getCartItemsInfo(cartSettleReqVo);
        SystemAsserts.notNull(cartInfo);
        List<OrderItemRespVo> settleOrderItemVos = Lists.transform(cartInfo.getItems(), new
                ShopCartItemRespVo2OrderItemRespVo());
        OrderSettlePageRespVoBuilder builder = OrderSettlePageRespVoBuilder.createBuilder();
        builder.setBuyerInfo(userInfo);
        builder.setItems(settleOrderItemVos);
        this.validProduct(reqVo, settleOrderItemVos);
        if (reqVo instanceof OrderCreateReqVo) {
            //
        } else {
            builder.setPaymentTypes(paymentService.getSupportedPaymentTypes(userId));
            OrderPromotionRespVO promotion = this.getUsablePromotion(userInfo, settleOrderItemVos);
            if (promotion != null) {
                List<OrderPromotionRespVo> promotionRespVos = Lists.transform(promotion.getActivePromotionItems(),
                        OrderPromotionRespVo::new);
                builder.setPromotions(promotionRespVos);
                builder.setFreeAmount(promotion.getPromotionCutOrderAmount());
            }
            //根据促销信息获取优惠券数量
            Integer couponCount = this.getUsableCouponCount(reqVo, this.filterCouponProduct(settleOrderItemVos, promotion));
            builder.setCoupons(couponCount);
            builder.setVoucherAmount(0); //TODO 获取优惠券抵扣金额

        }
        OrderSettlePageRespVo settleResult = builder.build();
        if (logger.isDebugEnabled()) {
            logger.debug("订单结算-------请求: {}, 返回值: {}", reqVo, settleResult);
        }
        return settleResult;
    }

    //过滤请求优惠券商品信息
    private List<ProductInfoVo> filterCouponProduct(List<OrderItemRespVo> settleOrderItemVos, OrderPromotionRespVO promotion) {
        List<ProductInfoVo> couponProducts = newArrayList();
        if (promotion != null && CollectionUtils.isNotEmpty(promotion.getActivePromotionItems())){
            for (OrderItemRespVo settleOrderItemVo : settleOrderItemVos) {
                boolean valid = false;
                for (OrderActivePromotionItemVO promotionItemVO : promotion.getActivePromotionItems()) {
                    if (settleOrderItemVo.getProductId().equals(promotionItemVO.getProductId()) && promotionItemVO.getAllowVoucher())     {
                        valid = true;
                    }
                }
                if (valid){
                    couponProducts.add(settleOrderItemVo);
                }
            }
        }
        return couponProducts;
    }

    private OrderPromotionRespVO getUsablePromotion(UserInfoVo userInfo, List<? extends IProduct>  products) {
        OrderPromotionReqVo promoReqVo = new OrderPromotionReqVo();
        int orderAmount = OrderUtil.calcOrderAmount(products);
        promoReqVo.setUserId(Long.valueOf(userInfo.getUserId()));
        promoReqVo.setCompanyGroupId(userInfo.getCompanyGroupId());
        promoReqVo.setProducts(products);
        promoReqVo.setOrderAmount(orderAmount);
        // 获取促销信息
//        MicroServiceResult<OrderPromotionRespVO> promotionResult = promotionFeignClient
//                .orderProductsPromotion(promoReqVo);
//        if (logger.isDebugEnabled()) {
//            logger.debug("满足促销: {}", promotionResult);
//        }
//        if (promotionResult != null && promotionResult.isSuccess()) {
//            return promotionResult.getData();
//        }
        return null;
    }

    //获取可用优惠券
    private Integer getUsableCouponCount(OrderSettlePageReqVo reqVo, List<ProductInfoVo> products) {
        OrderCouponReqVo couponReqVo = new OrderCouponReqVo();
        int orderAmount = OrderUtil.calcOrderAmount(products);
        couponReqVo.setUserId(Long.valueOf(reqVo.getUserId()));
        couponReqVo.setPaymentType(reqVo.getPaymentType());
        couponReqVo.setProducts(products);
        couponReqVo.setOrderAmount(orderAmount);
        Integer usableCount = ValueUtils.getValue(voucherFeignClient.getUsableCount(couponReqVo));
        if (logger.isDebugEnabled()) {
            logger.debug("请求可用优惠券-------请求vo: {}, 返回: {}", couponReqVo, usableCount);
        }
        return usableCount;
    }

    /**
     * 创建订单
     */
    @Transactional
    @Override
    public PaymentRespVo createPrePayOrder(OrderCreateReqVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("创建订单-------请求vo: {}", reqVo);
        }
        Order order = this.createOrder(reqVo);
        if (PaymentType.PAY_ON_DELIVERY.getValue() == reqVo.getPaymentType()) {
            return paymentService.noNeedPay(order);
        } else if (PaymentType.ALIPAY.getValue() == reqVo.getPaymentType()) {
            return paymentService.getAlipaySign(order);
        } else if (PaymentType.WECHAT.getValue() == reqVo.getPaymentType()) {
            return paymentService.wechatPay((OrderCreateWechatReqVo) reqVo, order);
        }

        throw new PaymentException("无效的支付方式");
    }

    @Transactional
    @Override
    public void applyReturn(OrderApplyReturnReqVo reqVo) {
        Order order = super.getOrder(reqVo.getOrderId());
        BusinessAsserts.notNull(order, DepotNextDoorExceptions.Order.ORDER_NOT_EXIST);
        BusinessAsserts.isTrue(order.isReturnable(false), DepotNextDoorExceptions.Order.ORDER_NOT_ALLOWED_RETURN);
        super.updateOrderStatus(order, OrderStatus.APPLY_RETURN);
        String returnCode = sequenceService.generateReturnCode();
        OrderReturn orderReturn = OrderReturnBuilder.createBuilder(reqVo, idService).setOrder(order).build(idService
                .nextId(), returnCode);


        orderReturnRepository.save(orderReturn);
        order.setOrderReturnId(orderReturn.getId());
    }

    @Override
    public Order getOrder(Long id) {
        return super.getOrder(id);
    }

    @Override
    public void saveOrder(Order order) {
        super.saveOrder(order);
    }

    /*****************public end*********************/


    private List<OrderRespVo> buildOrderVos(List<Order> orders) throws DepotNextDoorException {
        return orders.stream().map(o -> OrderRespVoBuilder.createBuilder(o).setItems(o.getItems()).build()).collect
                (Collectors.toList());
    }

    /**
     * 创建订单
     */
    private Order createOrder(OrderCreateReqVo reqVo) throws DepotNextDoorException {
        Timers timers = Timers.createAndBegin(logger.isDebugEnabled());
        OrderSettlePageRespVo settleResult = this.getSettleResult(reqVo);
        SystemAsserts.notNull(settleResult, "未获取到订单结算信息");
        List<OrderItemRespVo> items = settleResult.getItems();
        SystemAsserts.notEmpty(items, "未获取到结算明细信息");

        //TODO 使用和保存优惠券
        //TODO 保存促销活动
        long id = idService.nextId();
        String orderCode = sequenceService.generateOrderCode();
        Order order = OrderBuilder.createBuilder(reqVo).setUserInfo(settleResult.getUserInfoVo()).setItems(this.transOrderItems(items)).setFreeAmount
                (settleResult.getOrderAmount()).setVoucherAmount(settleResult.getVoucherAmount()).setPayAmount
                (settleResult.getPayAmount()).setPaymentType(PaymentType.valueOf(reqVo.getPaymentType())).build(id,
                orderCode);

        this.lockStock(order);
        //清空购物车
        shopCartService.cleanCart(reqVo);
        timers.print("创建订单用时");
        return order;
    }

    //锁定库存
    private void lockStock(Order order) throws DepotNextDoorException {
        List<UpdateCompanyLockStockReqVO> lockStockReqVOS = newArrayList();

        UpdateCompanyLockStockReqVO lockReqVo = new UpdateCompanyLockStockReqVO();
        lockReqVo.setOrderCode(order.getOrderCode());
        lockReqVo.setCompanyId(order.getSellerId());
        List<StockItemVO> items = Lists.transform(order.getItems(), new OrderItem2StockItemVO(false));
        lockReqVo.setItems(items);

        try {
            stockFeignClient.orderUpdateLockStocks(lockStockReqVOS);
        } catch (Exception e) {
            logger.error("锁定库存出错", e);
            throw e;
        }
    }

    private List<OrderItem> transOrderItems(List<OrderItemRespVo> items) {
        List<OrderItem> orderItems = newArrayList();
        for (OrderItemRespVo item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(idService.nextId());
            orderItem.setProductId(item.getProductId());
            orderItem.setProductCode(item.getProductCode());
            orderItem.setName(item.getName());
            orderItem.setLogo(item.getLogo());
            orderItem.setPrice(item.getSalePrice());
            orderItem.setMarketPrice(item.getMarketPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setItemType(item.getItemType());
            orderItems.add(orderItem);
        }

        return orderItems;
    }

    private void validProduct(OrderSettlePageReqVo reqVo, List<OrderItemRespVo> settleOrderItemVos) {
        SystemAsserts.isTrue(reqVo.getItems().size() == settleOrderItemVos.size(), "商品不存在");
        for (ProductItemReqVo reqItemVo : reqVo.getItems()) {
            for (OrderItemRespVo settleItemVo : settleOrderItemVos) {
                if (Objects.equals(reqItemVo.getProductId(), settleItemVo.getProductId())) {
                    SystemAsserts.isTrue(settleItemVo.canBuy(), String.format("商品[%s]已经下架不能购买!不能下单"));
                    SystemAsserts.isTrue(ValueUtils.getValue(reqItemVo.getQuantity() <= ValueUtils.getValue(settleItemVo.getStock())), String.format("商品[%s]库存不足!不能下单"));
                }
            }
        }
    }


}
