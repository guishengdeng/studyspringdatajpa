package com.biz.manage.controller.order;

import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.vo.order.req.OrderReturnAuditReqVo;
import com.biz.gbck.vo.order.req.OrderReturnSearchReqVo;
import com.biz.manage.controller.BaseController;
import com.biz.manage.servlet.ManageServlet;
import com.biz.service.order.backend.OrderReturnBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liqi1 on 2017/5/18.
 */
@Controller
@RequestMapping("/orderReturn")
public class OrderReturnController extends BaseController {

    @Autowired
    private OrderReturnBackendService orderReturnService;

    @GetMapping
//    @PreAuthorize("hasAuthority('OPT_ORDERRETURN_LIST')")
    public ModelAndView list(@ModelAttribute("reqVo") OrderReturnSearchReqVo reqVo){
        Page<OrderReturn> orderReturns = orderReturnService.searchOrderReturn(reqVo);
        return new ModelAndView("order/orderReturn/searchResult","orderReturns",orderReturns);
    }

    @GetMapping(value = "/detail")
//    @PreAuthorize("hasAuthority('OPT_ORDERRETURN_ITEM')")
    public ModelAndView getItem(Long id){
        OrderReturn orderReturn = orderReturnService.getOrderReturn(id);
        return new ModelAndView("order/orderReturn/detail","orderReturn",orderReturn);
    }

    @PostMapping(value = "audit")
//    @PreAuthorize("hasAuthority('OPT_ORDERRETURN_AUDIT')")
    public ModelAndView audit(OrderReturnAuditReqVo reqVo){
        String name = ManageServlet.getAdmin().getName();
        reqVo.setAuditor(name);
        orderReturnService.auditOrderReturn(reqVo);
        return new ModelAndView("redirect:/orderReturn");
    }

    @GetMapping(value = "audit")
//    @PreAuthorize("hasAuthority('OPT_ORDERRETURN_AUDIT')")
    public ModelAndView audit(Long id){
        OrderReturn orderReturn = orderReturnService.getOrderReturn(id);
        return new ModelAndView("order/orderReturn/audit","orderReturn",orderReturn);
    }

}
