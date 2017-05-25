package com.biz.soa.feign.client.payment;

import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.payment.req.IWechatPaymentReqVo;
import com.biz.gbck.vo.payment.req.WechatOrderReqVo;
import com.biz.gbck.vo.payment.resp.AlipaySignRespVo;
import com.biz.gbck.vo.payment.resp.PaidRespVo;
import com.biz.gbck.vo.payment.resp.PaymentQueryResultRespVo;
import com.biz.gbck.vo.payment.resp.WechatPayResp;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.pay.wechat.res.WechatPayNotifyRespVo;
import com.biz.soa.feign.hystrix.payment.PaymentFeignClientHystrix;
import com.biz.support.web.handler.JSONResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 *
 * @author lei
 * @date 2017年05月16日
 * @reviewer
 * @see
 */
@FeignClient(name = "soa-order", fallback = PaymentFeignClientHystrix.class)
public interface PaymentFeignClient {

    //查询支付状态
    @RequestMapping("/soa/payment/app/queryPaid")
    MicroServiceResult<PaymentQueryResultRespVo> queryPaid(@RequestBody IdReqVo reqVo);

    //获取支付宝支付参数
    @RequestMapping("/soa/payment/app/alipay")
    MicroServiceResult<AlipaySignRespVo> getAlipaySign(@RequestParam("orderId") Long orderId);

    //获取支付宝支付参数
    @RequestMapping("/soa/payment/app/wechat")
    MicroServiceResult<WechatPayResp> getWechatParam(@RequestBody IWechatPaymentReqVo req);

    //支付宝继续支付
    @RequestMapping("/soa/payment/app/alipay")
    JSONResult alipay(@RequestBody IdReqVo reqVo);

    //微信继续支付
    @RequestMapping("/soa/payment/app/wechat")
    JSONResult wechat(@RequestBody WechatOrderReqVo reqVo);

    //通知, 查询微信支付状态
    @RequestMapping("/soa/payment/notify/queryWechatPaid")
    PaidRespVo queryWechatPaid(@RequestParam("tradeNo") String tradeNo, @RequestParam("paymentId") Long paymentId,
                               @RequestParam("appId") String appId);
    //通知, 微信支付结果保存
    @RequestMapping("/soa/payment/notify/recordWechatNotify")
    void recordWechatNotify(@RequestBody WechatPayNotifyRespVo reqVo);

    //通知, 支付宝支付结果保存
    @RequestMapping("/soa/payment/notify/recordAlipayNotify")
    void recordAlipayNotify(Map<String, String> map);


    @RequestMapping(value = "/soa/payment/app/test", method = RequestMethod.GET)
    String getTestString();

}
