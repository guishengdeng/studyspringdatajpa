package com.biz.soa.feign.hystrix.order;

import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.PageRespVo;
import com.biz.gbck.vo.order.req.*;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;
import com.biz.soa.feign.client.order.OrderFeignClient;
import org.springframework.stereotype.Component;

/**
 *
 * @author lei
 * @date 2017年05月16日
 * @reviewer
 * @see
 */
@Component
public class OrderFeignClientHystrix implements OrderFeignClient {

    @Override
    public PageRespVo allTypeOrders(OrderListReqVo reqVo) {
        return null;
    }

    @Override
    public OrderRespVo orderDetail(IdReqVo reqVo) {
        return null;
    }

    @Override
    public void cancelOrder(IdReqVo reqVo) {

    }

    @Override
    public OrderSettlePageRespVo settle(OrderSettlePageReqVo reqVo) {
        return null;
    }

    @Override
    public PaymentRespVo createOrderNoPay(OrderCreateReqVo reqVo) {
        return null;
    }

    @Override
    public PaymentRespVo createOrderAlipay(OrderCreateReqVo reqVo) {
        return null;
    }

    @Override
    public PaymentRespVo createOrderWechat(OrderCreateWechatReqVo reqVo) {
        return null;
    }

    @Override
    public void applyReturn(OrderApplyReturnReqVo reqVo) {

    }

    @Override
    public String getTestString() {
        return null;
    }


}
