package com.biz.soa.feign.client.order;

import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.order.req.*;
import com.biz.gbck.vo.order.resp.OrderListRespVo;
import com.biz.gbck.vo.order.resp.OrderRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.payment.resp.PaymentRespVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.feign.hystrix.order.OrderFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lei
 * @date 2017年05月16日
 * @reviewer
 * @see
 */
//
@FeignClient(name = "soa-order", fallback = OrderFeignClientHystrix.class)
public interface OrderFeignClient {

    @RequestMapping(value = "/soa/order/app/list", method = RequestMethod.POST)
    MicroServiceResult<OrderListRespVo> allTypeOrders(@RequestBody OrderListReqVo reqVo);

    @RequestMapping(value = "/soa/order/app/detail", method = RequestMethod.POST)
    MicroServiceResult<OrderRespVo> orderDetail(@RequestBody IdReqVo reqVo);

    @RequestMapping(value = "/soa/order/app/cancel", method = RequestMethod.POST)
    MicroServiceResult cancelOrder(@RequestBody IdReqVo reqVo);

    @RequestMapping(value = "/soa/order/app/settle", method = RequestMethod.POST)
    MicroServiceResult<OrderSettlePageRespVo> settle(@RequestBody OrderSettlePageReqVo reqVo);

    @RequestMapping(value = "/soa/order/app/createOrderNoPay", method = RequestMethod.POST)
    MicroServiceResult<PaymentRespVo>  createOrderNoPay(@RequestBody OrderCreateReqVo reqVo );

    @RequestMapping(value = "/soa/order/app/createOrderAlipay", method = RequestMethod.POST)
    MicroServiceResult<PaymentRespVo>  createOrderAlipay(@RequestBody OrderCreateReqVo reqVo);

    @RequestMapping(value = "/soa/order/app/createOrderWechat", method = RequestMethod.POST)
    MicroServiceResult<PaymentRespVo>  createOrderWechat(@RequestBody OrderCreateWechatReqVo reqVo);

    @RequestMapping(value = "/soa/order/app/applyReturn", method = RequestMethod.POST)
    MicroServiceResult applyReturn(@RequestBody OrderApplyReturnReqVo reqVo  );

    @RequestMapping(value = "/soa/order/app/test", method = RequestMethod.GET)
    MicroServiceResult<String> getTestString();

}
