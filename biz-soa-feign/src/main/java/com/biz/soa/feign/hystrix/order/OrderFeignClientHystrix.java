package com.biz.soa.feign.hystrix.order;

import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.order.req.*;
import com.biz.gbck.vo.order.resp.OrderListRespVo;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
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
    public MicroServiceResult<OrderListRespVo> allTypeOrders(OrderListReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<OrderRespVo> orderDetail(IdReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult cancelOrder(IdReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<OrderSettlePageRespVo> settle(OrderSettlePageReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<PaymentRespVo> createOrderNoPay(OrderCreateReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<PaymentRespVo> createOrderAlipay(OrderCreateReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<PaymentRespVo> createOrderWechat(OrderCreateWechatReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult applyReturn(OrderApplyReturnReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<String> getTestString() {
        return null;
    }


}
