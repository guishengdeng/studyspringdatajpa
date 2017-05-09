package com.biz.soa.order.service.frontend;

import com.biz.core.asserts.SystemAsserts;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.repository.order.OrderRepository;
import com.biz.gbck.dao.redis.repository.order.OrderRedisDao;
import com.biz.gbck.enums.order.OrderShowStatus;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.order.PaymentException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.PageRespVo;
import com.biz.gbck.vo.order.req.*;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.order.frontend.OrderFrontendService;
import com.biz.soa.builder.OrderRespVoBuilder;
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
    private PaymentService paymentService;

    /*****************public begin*********************/

    @Override
    public PageRespVo listOrders(OrderListReqVo reqVo) {
        SystemAsserts.notNull(reqVo);
        OrderShowStatus status = OrderShowStatus.valueOf(reqVo.getStatus());
        SystemAsserts.notNull("status", "订单状态不合法");
        List<Long> orderIds = orderRedisDao.findOrderIdsByUserIdWithPeriod(Long.valueOf(reqVo
                .getUserId()), status, reqVo.getPage(), reqVo.getSize());
        List<Order> orders = orderRepository.findAll(orderIds);
        List<OrderRespVo> orderRespVos = this.buildOrderVos(orders);

        return new PageRespVo(reqVo.getPage(), orderRespVos);
    }

    @Override
    public OrderRespVo getOrderDetail(IdReqVo reqVo) {
        SystemAsserts.notNull(reqVo);
        Order order = orderRepository.findOne(reqVo.getId());
        return CollectionUtils.getFirstNotNullValue(this.buildOrderVos(newArrayList(order)));
    }

    @Override
    public void cancelOrder(IdReqVo reqVo) {
        //TODO 取消订单
    }

    @Override
    public OrderSettlePageRespVo settle(OrderSettlePageReqVo reqVo) {
        return null;
    }

    /**
     * 创建订单
     */
    @Transactional
    @Override
    public PaymentRespVo createPrePayOrder(OrderCreateReqVo reqVo) throws DepotNextDoorException {
        Order order = this.createOrder(reqVo);
        if (PaymentType.PAY_ON_DELIVERY.getValue() == reqVo.getPaymentType()) {
            paymentService.noNeedPay(order);
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


    private List<OrderRespVo> buildOrderVos(List<Order> orders) {
        List<OrderRespVo> orderRespVos = newArrayList();
        for (Order order : orders) {
            OrderRespVo respVo = OrderRespVoBuilder.createBuider(order).setSellerInfo(null).setBuyerInfo(null, null,
                    null).setItems(order.getItems()).build();
            orderRespVos.add(respVo);
        }
        return orderRespVos;
    }

    /**
     * 创建订单
     */
    private Order createOrder(OrderCreateReqVo reqVo) {
            //TODO
        return null;
    }


}
