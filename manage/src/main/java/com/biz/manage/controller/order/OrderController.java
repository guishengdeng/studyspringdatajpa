package com.biz.manage.controller.order;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.vo.order.req.OrderQueryReqVo;
import com.biz.gbck.vo.platform.PartnerRespVo;
import com.biz.gbck.vo.platform.PlatFormRespVo;
import com.biz.service.order.backend.OrderBackendService;
import com.biz.service.org.interfaces.PlatformService;
import com.biz.service.partner.interfaces.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * OrderController
 *
 * @author guisheng.deng
 * @date 2017年05月18日
 * @reviewer
 * @description
 * @see
 */
@Secured("ROLE_ORDER")
@Controller
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderBackendService orderBackendService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private PartnerService partnerService;

    @GetMapping
    @PreAuthorize("hasAuthority('OPT_ORDER_LIST')")
    public ModelAndView list(@ModelAttribute OrderQueryReqVo vo){
        Page<Order> page = orderBackendService.queryOrdersByCondition(vo);
        List<PlatFormRespVo> platFormRespVo = platformService.getNotDuplicatedName();
        //List<PartnerRespVo> partnerRespVos = partnerService.getNotDuplicatePartnerName();
        return new ModelAndView("ord/order/list","orderPage",orderBackendService.pageOrder2PageRespVo(page))
                .addObject("platFormRespVo",platFormRespVo);
    }
    @PostMapping("/findPartners")
    @PreAuthorize("hasAuthority('OPT_ORDER_LIST')")
    @ResponseBody
    public List<PartnerRespVo> findPartnersByPlatFormId(Long platFormId){
        return partnerService.getPartnersByPlatFormId(platFormId);
    }
}