package com.biz.manage.controller.payment;

import com.biz.gbck.dao.mysql.po.payment.WechatPaymentLogPo;
import com.biz.gbck.vo.payment.AlipayPaymentVo;
import com.biz.gbck.vo.payment.WechatPaymentVo;
import com.biz.service.payment.interf.WechatPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lzz on 2017/5/15.
 */
@Controller
@RequestMapping("pay")
@Secured("ROLE_PAY")
public class WechatPaymentController {

    @Autowired
    private WechatPaymentService wechatPaymentService;

    @RequestMapping("wechatpay")
    @PreAuthorize("hasAuthority('OPT_ALIPAY_LIST')")
    public ModelAndView findList(@ModelAttribute("wechatPaymentVo") WechatPaymentVo wechatPaymentVo) {
        Page<WechatPaymentLogPo> page = wechatPaymentService.findList(wechatPaymentVo);
        return new ModelAndView("payment/wechatpay").addObject("page", page);
    }

    @RequestMapping("wechatpaylog")
    @PreAuthorize("hasAuthority('OPT_ALIPAY_LIST')")
    @ResponseBody
    public WechatPaymentLogPo listOne(Long id){
        return wechatPaymentService.findOne(id);
    }
}