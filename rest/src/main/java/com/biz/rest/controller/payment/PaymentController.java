package com.biz.rest.controller.payment;

import com.biz.gbck.exceptions.order.PaymentException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.payment.req.WechatOrderReqVo;
import com.biz.gbck.vo.payment.resp.AlipaySignRespVo;
import com.biz.gbck.vo.payment.resp.PaymentQueryResultRespVo;
import com.biz.gbck.vo.payment.resp.WechatPayResp;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.util.RestUtil;
import com.biz.soa.feign.client.payment.PaymentFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付相关 controller
 *
 * @author lei
 * @date 2017年04月25日
 * @reviewer
 * @see
 */
@RestController
@RequestMapping("/payment")
public class PaymentController extends BaseRestController {

    @Autowired(required = false)
    private PaymentFeignClient paymentFeignClient;

    //查询支付状态
    @RequestMapping("/queryPaid")
    public JSONResult queryPaid(HttpServletRequest request) throws PaymentException {
        IdReqVo reqVo = RestUtil.parseBizData(request, IdReqVo.class);
        return RestUtil.parseBizResult(paymentFeignClient.queryPaid(reqVo));
    }

    //支付宝继续支付
    @RequestMapping("/alipay")
    public JSONResult alipay(HttpServletRequest request) throws PaymentException {
        IdReqVo reqVo = RestUtil.parseBizData(request, IdReqVo.class);
        return RestUtil.parseBizResult(paymentFeignClient.getAlipaySign(Long.valueOf(reqVo.getId())));
    }

    //微信继续支付
    @RequestMapping("/wechat")
    public JSONResult wecaht(HttpServletRequest request) throws PaymentException {
        WechatOrderReqVo reqVo = RestUtil.parseBizData(request, WechatOrderReqVo.class);
        return RestUtil.parseBizResult(paymentFeignClient.getWechatParam(reqVo));
    }




}
