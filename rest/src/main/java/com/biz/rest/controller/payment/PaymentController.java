package com.biz.rest.controller.payment;

import com.biz.gbck.exceptions.order.PaymentException;
import com.biz.gbck.vo.IdReqVo;
import com.biz.gbck.vo.payment.req.WechatOrderReqVo;
import com.biz.gbck.vo.payment.resp.AlipaySignRespVo;
import com.biz.gbck.vo.payment.resp.PaymentQueryResultResponseVo;
import com.biz.gbck.vo.payment.resp.WechatPayResp;
import com.biz.rest.controller.BaseRestController;
import com.biz.soa.order.service.payment.PaymentService;
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
@RequestMapping("/payemnt")
public class PaymentController extends BaseRestController {

    @Autowired
    private PaymentService paymentService;

    //查询支付状态
    @RequestMapping("/queryPay")
    public JSONResult allTypeOrders(HttpServletRequest request) throws PaymentException {
        IdReqVo reqVo = super.parseBizData(request, IdReqVo.class);
        PaymentQueryResultResponseVo paidResultRespVo = paymentService.queryPaid(reqVo);
        return new JSONResult(paidResultRespVo);
    }

    //支付宝继续支付
    @RequestMapping("/alipay")
    public JSONResult orderDetail(HttpServletRequest request) throws PaymentException {
        IdReqVo reqVo = super.parseBizData(request, IdReqVo.class);
        AlipaySignRespVo responseVo = paymentService.getAlipaySign(reqVo.getId());
        return new JSONResult(responseVo);
    }

    //微信继续支付
    @RequestMapping("/wechat")
    public JSONResult cancelOrder(HttpServletRequest request) throws PaymentException {
        WechatOrderReqVo reqVo = super.parseBizData(request, WechatOrderReqVo.class);
        WechatPayResp respVo = paymentService.wechatPay(reqVo, reqVo.getOrderId());
        return new JSONResult(respVo);
    }




}
