package com.biz.manage.controller.product;

import com.biz.gbck.dao.mysql.po.purchase.PurchaseOrderItem;
import com.biz.gbck.vo.product.backend.ProductSaleDetailQueryReqVo;
import com.biz.gbck.vo.product.frontend.ProductSaleDetailRespVo;
import com.biz.service.product.backend.ProductSaleDetailBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * ProductSaleDetailController
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
@Controller
@Secured("ROLE_SALEDETAIL")
@RequestMapping("saleDetail")
public class ProductSaleDetailController {

    @Autowired
    private ProductSaleDetailBackendService productSaleDetailBackendService;

    @GetMapping
    @PreAuthorize("hasAuthority('OPT_SALEDETAIL_LIST')")
    public ModelAndView getRespVoList(ProductSaleDetailQueryReqVo vo){
         Page<PurchaseOrderItem> page =  productSaleDetailBackendService.queryOrderItemByCondition(vo);
         return  new ModelAndView("product/sale/list","productPage",
                 productSaleDetailBackendService.pagePo2PageRespVo(page));
    }
}