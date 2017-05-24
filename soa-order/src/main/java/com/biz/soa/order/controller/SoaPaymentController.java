package com.biz.soa.order.controller;

import com.biz.gbck.exceptions.order.PaymentException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.payment.req.WechatOrderReqVo;
import com.biz.gbck.vo.payment.resp.AlipaySignRespVo;
import com.biz.gbck.vo.payment.resp.PaidRespVo;
import com.biz.gbck.vo.payment.resp.PaymentQueryResultRespVo;
import com.biz.gbck.vo.payment.resp.WechatPayResp;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.pay.wechat.res.WechatPayNotifyRespVo;
import com.biz.soa.base.SoaBaseController;
import com.biz.soa.order.service.payment.PaymentService;
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
    public MicroServiceResult<PaymentQueryResultRespVo> queryPaid(@RequestBody IdReqVo reqVo) {
        try {
            return render200(paymentService.queryPaid(reqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }

    //支付宝继续支付
    @RequestMapping("/app/alipay")
    public MicroServiceResult<AlipaySignRespVo> alipay(@RequestBody IdReqVo reqVo) {
        try {
            return render200(paymentService.getAlipaySign(reqVo.getId()));
        } catch (Exception e) {
            return render500(e);
        }
    }

    //微信继续支付
    @RequestMapping("/app/wechat")
    public MicroServiceResult<WechatPayResp> wecaht(@RequestBody WechatOrderReqVo reqVo) {
        try {
            return render200(paymentService.wechatPay(reqVo, reqVo.getOrderId()));
        } catch (Exception e) {
            return render500(e);
        }
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
