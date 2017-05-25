package com.biz.soa.feign.hystrix.payment;

import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.payment.req.IWechatPaymentReqVo;
import com.biz.gbck.vo.payment.req.WechatOrderReqVo;
import com.biz.gbck.vo.payment.resp.AlipaySignRespVo;
import com.biz.gbck.vo.payment.resp.PaidRespVo;
import com.biz.gbck.vo.payment.resp.PaymentQueryResultRespVo;
import com.biz.gbck.vo.payment.resp.WechatPayResp;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.pay.wechat.res.WechatPayNotifyRespVo;
import com.biz.soa.feign.client.payment.PaymentFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lei
 * @date 2017年05月16日
 * @reviewer
 * @see
 */
@Component
public class PaymentFeignClientHystrix implements PaymentFeignClient {

    @Override
    public MicroServiceResult<PaymentQueryResultRespVo> queryPaid(IdReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<AlipaySignRespVo> getAlipaySign(Long orderId) {
        return null;
    }

    @Override
    public MicroServiceResult<WechatPayResp> getWechatParam(IWechatPaymentReqVo req) {
        return null;
    }

    @Override
    public JSONResult alipay(IdReqVo reqVo) {
        return null;
    }

    @Override
    public JSONResult wechat(WechatOrderReqVo reqVo) {
        return null;
    }

    @Override
    public PaidRespVo queryWechatPaid(String tradeNo, Long paymentId, String appId) {
        return null;
    }

    @Override
    public void recordWechatNotify(WechatPayNotifyRespVo reqVo) {

    }

    @Override
    public void recordAlipayNotify(Map<String, String> map) {

    }

    @Override
    public String getTestString() {
        return null;
    }
}
