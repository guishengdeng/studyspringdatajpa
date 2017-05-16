package com.biz.manage.controller.payment;

import com.biz.gbck.dao.mysql.po.payment.AlipayPaymentLogPo;
import com.biz.gbck.vo.payment.AlipayPaymentVo;
import com.biz.manage.controller.BaseController;
import com.biz.service.payment.interf.AlipayPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lzz on 2017/5/15.
 */
@Controller
@RequestMapping("payment")
@Secured("ROLE_PAY")
public class AlipayPaymentController extends BaseController {

    @Autowired
    private AlipayPaymentService alipayPaymentService;

    @GetMapping("alipay")
    @PreAuthorize("hasAuthority('OPT_ALIPAY_LIST')")
    public ModelAndView find(@ModelAttribute("alipayPaymentVo") AlipayPaymentVo alipayPaymentVo) {
        Page<AlipayPaymentLogPo> page = alipayPaymentService.findList(alipayPaymentVo);
        return new ModelAndView("payment/alipay").addObject("page", page);
    }

    @GetMapping("alipaylog")
    @PreAuthorize("hasAuthority('OPT_ALIPAY_LIST')")
    public ModelAndView list(Long id){
        AlipayPaymentLogPo alipayPaymentLogPo = alipayPaymentService.findById(id);
        return  new ModelAndView("payment/alipay").addObject("alipayPaymentLogPo",alipayPaymentLogPo);
    }
}
