package com.biz.soa.order.service.frontend;

import com.biz.core.asserts.SystemAsserts;
import com.biz.gbck.common.model.order.ICommonReqVoBindUserId;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.repository.order.OrderRepository;
import com.biz.gbck.dao.mysql.repository.order.OrderReturnRepository;
import com.biz.gbck.dao.redis.repository.order.OrderRedisDao;
import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.transform.order.Order2OrderRo;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.payment.resp.PaymentQueryResultRespVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.SequenceService;
import com.biz.service.cart.ShopCartService;
import com.biz.soa.feign.client.org.ShopFeignClient;
import com.biz.soa.feign.client.org.UserFeignClient;
import com.biz.soa.feign.client.product.PromotionFeignClient;
import com.biz.soa.feign.client.stock.StockFeignClient;
import com.biz.soa.feign.client.voucher.VoucherFeignClient;
import com.biz.soa.order.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Objects;

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

    @Autowired
    protected ShopCartService shopCartService;

    @Autowired(required = false)
    protected UserFeignClient userFeignClient;

    @Autowired(required = false)
    protected ShopFeignClient shopFeignClient;

    @Autowired(required = false)
    protected StockFeignClient stockFeignClient;

    @Autowired(required = false)
    protected VoucherFeignClient voucherFeignClient;

    @Autowired(required = false)
    protected PromotionFeignClient promotionFeignClient;

    protected Order getOrder(Long id) {
        return orderRepository.findOne(id);
    }

    @Transactional
    protected Order saveOrder(Order order) {
        saveOrUpdateUsingPo(orderRepository, orderRedisDao, order, new Order2OrderRo());
        return order;
    }

    protected Order updateOrderStatus(Order order, OrderStatus newStatus) {
        logger.debug("修改订单状态 orderId={}. {} --> {}", order.getStatus(), newStatus);
        SystemAsserts.notNull(newStatus, "新订单状态不能为空");
        order.setStatus(newStatus);
        this.saveOrder(order);
        return order;
    }

    /**
     * 使用支付接口查询支付状态
     *
     * @param order
     */
    protected boolean queryPayStatus(Long orderId) throws DepotNextDoorException {
        PaymentQueryResultRespVo paidResult = paymentService.queryPaid(new IdReqVo(String.valueOf(orderId)));
        if (logger.isDebugEnabled()) {
            logger.debug("订单[orderId={}]查询支付结果", orderId);
        }
        return paidResult.isPaid();
    }

    /**
     * 校验用户操作订单
     * @param order
     * @param reqVo
     */
    public void validUser(Order order, ICommonReqVoBindUserId reqVo) {
        SystemAsserts.notNull(order, "订单不存在");
        SystemAsserts.isTrue(Objects.equals(order.getUserId(), Long.valueOf(reqVo.getUserId())), "用户不能操作该订单");
    }
}
