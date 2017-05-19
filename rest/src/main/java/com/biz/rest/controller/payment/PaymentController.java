package com.biz.rest.controller.payment;

import com.biz.rest.controller.BaseRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @Autowired(required = false)
//    private PaymentService paymentService;
//
//    //查询支付状态
//    @RequestMapping("/queryPaid")
//    public JSONResult queryPaid(HttpServletRequest request) throws PaymentException {
//        IdReqVo reqVo = RestUtil.parseBizData(request, IdReqVo.class);
//        PaymentQueryResultRespVo paidResultRespVo = paymentService.queryPaid(reqVo);
//        return new JSONResult(paidResultRespVo);
//    }
//
//    //支付宝继续支付
//    @RequestMapping("/alipay")
//    public JSONResult alipay(HttpServletRequest request) throws PaymentException {
//        IdReqVo reqVo = RestUtil.parseBizData(request, IdReqVo.class);
//        AlipaySignRespVo responseVo = paymentService.getAlipaySign(reqVo.getId());
//        return new JSONResult(responseVo);
//    }
//
//    //微信继续支付
//    @RequestMapping("/wechat")
//    public JSONResult wecaht(HttpServletRequest request) throws PaymentException {
//        WechatOrderReqVo reqVo = RestUtil.parseBizData(request, WechatOrderReqVo.class);
//        WechatPayResp respVo = paymentService.wechatPay(reqVo, reqVo.getOrderId());
//        return new JSONResult(respVo);
//    }




}
