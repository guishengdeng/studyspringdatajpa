package com.biz.soa.order.controller;

import com.biz.gbck.exceptions.order.PaymentException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.payment.req.WechatOrderReqVo;
import com.biz.gbck.vo.payment.resp.AlipaySignRespVo;
import com.biz.gbck.vo.payment.resp.PaidRespVo;
import com.biz.gbck.vo.payment.resp.PaymentQueryResultRespVo;
import com.biz.gbck.vo.payment.resp.WechatPayResp;
import com.biz.pay.wechat.res.WechatPayNotifyRespVo;
import com.biz.soa.base.SoaBaseController;
import com.biz.soa.order.service.payment.PaymentService;
import com.biz.support.web.handler.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单soa controller
 *
 * @author lei
 * @date 2017年05月14日
 * @reviewer
 * @see
 */
@RestController
@RequestMapping(value = "/soa/payment")
public class SoaPaymentController extends SoaBaseController {

    @Autowired
    private PaymentService paymentService;

    //查询支付状态
    @RequestMapping("/app/queryPaid")
    public JSONResult queryPaid(@RequestBody IdReqVo reqVo) throws PaymentException {
        PaymentQueryResultRespVo paidResultRespVo = paymentService.queryPaid(reqVo);
        return new JSONResult(paidResultRespVo);
    }

    //支付宝继续支付
    @RequestMapping("/app/alipay")
    public JSONResult alipay(@RequestBody IdReqVo reqVo) throws PaymentException {
        AlipaySignRespVo responseVo = paymentService.getAlipaySign(reqVo.getId());
        return new JSONResult(responseVo);
    }

    //微信继续支付
    @RequestMapping("/app/wechat")
    public JSONResult wecaht(@RequestBody WechatOrderReqVo reqVo) throws PaymentException {
        WechatPayResp respVo = paymentService.wechatPay(reqVo, reqVo.getOrderId());
        return new JSONResult(respVo);
    }




    //查询微信支付状态
    @RequestMapping("/notify/queryWechatPaid")
    public PaidRespVo queryWechatPaid(@RequestParam("tradeNo") String tradeNo, @RequestParam("paymentId") Long paymentId,
                               @RequestParam("appId") String appId) throws PaymentException {
        return paymentService.queryWechatPaid(tradeNo, paymentId, appId);
    }

    //记录微信支付通知
    @RequestMapping("/notify/recordWechatNotify")
    public void recordWechatNotify(@RequestBody WechatPayNotifyRespVo reqVo)  {
        paymentService.wechatTradeNotify(reqVo);
    }

    //记录支付宝支付通知
    @RequestMapping("/soa/payment/notify/recordAlipayNotify")
    public void recordAlipayNotify(Map<String, String> map)  {
         paymentService.aliNotify(map);
    }

    @GetMapping(value = "/app/test")
    public String getTestString() {
        return "I am a test String";
    }

}
