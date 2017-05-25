package com.biz.manage.controller.extendproperty;

import com.biz.gbck.dao.mysql.po.product.meta.ExtendProperty;
import com.biz.gbck.exceptions.product.ExtendPropertyNotFoundException;
import com.biz.gbck.vo.product.backend.CreateExtendPropertyVo;
import com.biz.gbck.vo.product.backend.ExtendPropertySortVo;
import com.biz.service.product.backend.CategoryService;
import com.biz.service.product.backend.ExtendPropertyService;
import com.biz.service.product.backend.ProductExtendService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * ExtendPropertyController
 *
 * @author guisheng.deng
 * @date 2017年05月04日
 * @reviewer
 * @description
 * @see
 */
@Controller
@Secured("ROLE_EXTENDPROPERTY")
@RequestMapping("product/extendProperty")
public class ExtendPropertyController {

    @Autowired(required = false)
    private ExtendPropertyService extendPropertyService;
    @Autowired(required = false)
    private ProductExtendService productExtendService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/detail/{id}")
    @PreAuthorize("hasAuthority('OPT_EXTENDPROPERTY_DETAIL')")
    public ModelAndView detail(@PathVariable(name = "id") Long productExtendId, HttpSession session) {
        /*JSONArray json = new JSONArray();
        if(extendPropertyService.findByProductExtendId(productExtendId) != null){
            for(ExtendProperty item : extendPropertyService.findByProductExtendId(productExtendId)){
                json.add(JSONObject.fromObject("{\"value\":\""+item.getValue()+"\",\"id\":\""+item.getId()+"\"}"));
            }
        }
        session.setAttribute("currJson",json);*/
        session.setAttribute("extendProperties",extendPropertyService.findByProductExtendId(productExtendId));
        session.setAttribute("productExtend",productExtendService.findOne(productExtendId));
        session.setAttribute("category",categoryService.findCategory(productExtendService.findOne(productExtendId).getCategory().getId()));
        return new ModelAndView("manage/extendproperty/list").addObject("productExtendId", productExtendId);

    }

    @RequestMapping("/saveSort")
    @PreAuthorize("hasAuthority('OPT_EXTENDPROPERTY_EDIT')")
    public String saveSort(ExtendPropertySortVo vo) {
        extendPropertyService.saveOrUpdateSort(vo);
        return "redirect:detail/" + vo.getProductExtendId() + ".do";
    }

    @GetMapping("/add/{productExtendId}")
    @PreAuthorize("hasAuthority('OPT_EXTENDPROPERTY_ADD')")
    public ModelAndView add(@PathVariable(name = "productExtendId") Long id) {

        return new ModelAndView("manage/extendproperty/add", "productExtendId", id)
                .addObject("extendProperties",extendPropertyService.findByProductExtendId(id))
                .addObject("cmd", "add");
    }

    @GetMapping("/edit/{extendPropertyId}/{productExtendId}")
    @PreAuthorize("hasAuthority('OPT_EXTENDPROPERTY_EDIT')")
    public ModelAndView edit(@PathVariable(name = "extendPropertyId") Long epId, @PathVariable(name = "productExtendId") Long peId) {

        return new ModelAndView("manage/extendproperty/add",
                "extendProperty", extendPropertyService.findById(epId))
                .addObject("productExtendId", peId)
                .addObject("extendProperties",extendPropertyService.findByProductExtendId(peId))
                .addObject("cmd", "edit");
    }
    @RequestMapping("/addOrUpdate")
    @PreAuthorize("hasAuthority('OPT_EXTENDPROPERTY_ADD')")
    @ResponseBody
    public boolean  addOrUpdate(CreateExtendPropertyVo vo){
        try {

            return  extendPropertyService.isExistExtendPropertyValue(vo);
        } catch (ExtendPropertyNotFoundException e) {
            return false;

        }
    }
    @RequestMapping("/again")
    @PreAuthorize("hasAuthority('OPT_EXTENDPROPERTY_ADD')")
    public ModelAndView  again(CreateExtendPropertyVo vo) throws ExtendPropertyNotFoundException {

        extendPropertyService.createExtendProperty(vo);
        return new ModelAndView("redirect:detail/" + vo.getProductExtendId() + ".do");

    }


}