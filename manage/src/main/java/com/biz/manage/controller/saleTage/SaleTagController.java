package com.biz.manage.controller.saleTage;


import com.biz.gbck.dao.mysql.po.tag.SaleTag;
import com.biz.gbck.vo.product.backend.SaleTagSearchVo;
import com.biz.gbck.vo.product.backend.SaleTagVo;
import com.biz.manage.controller.BaseController;
import com.biz.service.product.backend.SaleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商品管理的销售标签
 * Created by lzz on 2017/5/2.
 */
@Controller
@Secured("ROLE_SALETAG")
@RequestMapping("goodsmanagement")
public class SaleTagController extends BaseController {

    @Autowired
    private SaleTagService saleTagService;

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('OPT_SALETAG_LIST')")
    public ModelAndView list(@ModelAttribute("saleTagSearchVo") SaleTagSearchVo saleTagSearchVo) {
        Page<SaleTag> page = saleTagService.searchSaleTag(saleTagSearchVo);
        return new ModelAndView("goodsmanagement/searchSaleTag", "page", page);
    }

    //修改或添加页面
    @RequestMapping("/tag")
    @PreAuthorize("hasAuthority('OPT_SALETAG_ADD')")
    public ModelAndView saleTagaddOrUpdate() {
        return new ModelAndView("goodsmanagement/tag");
    }

    //添加修改标签
    @RequestMapping("/AddOrUpdate")
    @PreAuthorize("hasAuthority('OPT_SALETAG_ADD')")
    public ModelAndView addOrUpdate(SaleTagVo vo) {
        saleTagService.addOrUpdateSaleTag(vo);
        return new ModelAndView("redirect:search");
    }

    @RequestMapping("/find")
    @PreAuthorize("hasAuthority('OPT_SALETAG_FIND')")
    public ModelAndView findById(Long id) {
        SaleTag saleTag = saleTagService.findById(id);
        return new ModelAndView("goodsmanagement/tag", "saleTag", saleTag);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_SALETAG_DELETE')")
    @ResponseBody
    public boolean delete(@RequestParam("id") Long id) {
        try {
            saleTagService.remove(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}

