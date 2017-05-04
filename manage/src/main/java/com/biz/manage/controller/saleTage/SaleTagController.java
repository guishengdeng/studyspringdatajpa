package com.biz.manage.controller.saleTage;


import com.biz.gbck.dao.mysql.po.product.meta.SaleTag;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.CreateSaleTagVo;
import com.biz.gbck.vo.product.backend.SaleTagSearchVo;
import com.biz.service.product.backend.SaleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Generated;

/**
 * 商品管理的销售标签
 * Created by lzz on 2017/5/2.
 */
@Controller
@Secured("ROLE_SALETAG")
@RequestMapping("goodsmanagement")
public class SaleTagController {

    @Autowired
    private SaleTagService saleTagService;

    @GetMapping("/saleTag")
    @PreAuthorize("hasAuthority('OPT_SALETAG_LIST')")
    public ModelAndView list(@ModelAttribute("saleTagSearchVo")SaleTagSearchVo saleTagSearchVo,@RequestParam(value = "enable", required = false, defaultValue = "ENABLE") CommonStatusEnum status){
        Page<SaleTag> page=saleTagService.searchSaleTag(saleTagSearchVo);
        saleTagService.listByStatus(status);
        return new ModelAndView("goodsmanagement/saleTag","page",page);
    }
//    @GetMapping("/saleTag")
//    @PreAuthorize("hasAuthority('OPT_SALETAG_LIST')")
//    public ModelAndView saleTagList(@RequestParam(value = "enable", required = false, defaultValue = "ENABLE") CommonStatusEnum status) {
//
//        return new ModelAndView("goodsmanagement/saleTag", "saleTags", saleTagService.listByStatus(status)).addObject("enable", status.isEnable());
//
//    }

    //添加或修改的页面
    @RequestMapping("/addUpdate")
    @PreAuthorize("hasAuthority('OPT_SALETAG_ADD')")
    public ModelAndView saleTagaddOrUpdate() {
        return new ModelAndView("goodsmanagement/addUpdate");
    }

    //添加修改标签
    @RequestMapping("/addOrUpdate")
    @PreAuthorize("hasAuthority('OPT_SALETAG_ADDORUPDATE')")
    public ModelAndView addOrUpdate(CreateSaleTagVo vo) {
        saleTagService.addOrUpdateSaleTag(vo);
        return new ModelAndView("redirect:saleTag");
    }

    @RequestMapping("/find")
    @PreAuthorize("hasAuthority('OPT_SALETAG_FIND')")
    public ModelAndView findById(Long id) {
        SaleTag saleTag = saleTagService.findById(id);
        return new ModelAndView("goodsmanagement/addUpdate", "saleTag", saleTag);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_SALETAG_DELETE')")
    @ResponseBody
    public boolean delete(@RequestParam("id")Long id){
        try{
            saleTagService.remove(id);
            return true;
        }catch (Exception e){
            return  false;
        }

    }
}

