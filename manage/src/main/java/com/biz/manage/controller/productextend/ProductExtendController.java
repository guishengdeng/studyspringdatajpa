package com.biz.manage.controller.productextend;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.ProductExtendNotFoundException;
import com.biz.gbck.vo.product.backend.CreateCategoryPropertyVo;
import com.biz.gbck.vo.product.backend.PropertySortVo;
import com.biz.service.product.backend.ProductExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * ProduceExtendController
 *
 * @author guisheng.deng
 * @date 2017年05月03日
 * @reviewer
 * @description
 * @see
 */
@Controller
@Secured("ROLE_PRODUCTEXTEND")
@RequestMapping("product/categoryProperty")
public class ProductExtendController {
    @Autowired(required = false)
    private ProductExtendService productExtendService;

    @RequestMapping("/save_sort")
    @PreAuthorize("hasAuthority('OPT_PRODUCTEXTEND_ADD')")
    public String  saveSort(PropertySortVo vo){
        productExtendService.saveOrUpdateSort(vo);
        return "redirect:/manage/categories/"+vo.getCategoryId()+".do";
    }
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_PRODUCTEXTEND_ADD')")
    public String add(Model model){
        model.addAttribute("cmd","add");
        return "manage/productextend/add";
    }

    /**
     * RESFful风格的编程
     * @param propertyId
     * @param parentId
     * @return
     */
    @RequestMapping("/edit/{id}/{categoryId}")
    @PreAuthorize("hasAuthority('OPT_PRODUCTEXTEND_EDIT')")
    public ModelAndView edit(@PathVariable(name = "id") Long propertyId, @PathVariable(name = "categoryId") Long parentId){

        return new ModelAndView
                ("manage/productextend/add","property",
                        productExtendService.findOne(propertyId))
                .addObject("categoryId",parentId)
                .addObject("cmd","edit");
    }
    @RequestMapping("/isExist")
    @PreAuthorize("hasAuthority('OPT_PRODUCTEXTEND_ADD')")
    @ResponseBody
    public Boolean isExist(CreateCategoryPropertyVo vo) throws DepotNextDoorException {
        try {
            return productExtendService.isExistProductExtendName(vo);
        } catch (ProductExtendNotFoundException e) {
            return false;
        }
    }
    @RequestMapping("/again")
    @PreAuthorize("hasAuthority('OPT_PRODUCTEXTEND_ADD')")
    public ModelAndView again(CreateCategoryPropertyVo vo) throws DepotNextDoorException {
        productExtendService.createCategoryProperty(vo);
        return new ModelAndView("redirect:/product/categories/"+vo.getCategoryId()+".do");
    }



    @RequestMapping("/change")
    @PreAuthorize("hasAuthority('OPT_EXTENDPROPERTY_DETAIL')")
    @ResponseBody
    public Map<String , Object> change(@RequestParam("id") Long categoryId){
        Map<String,Object> map = new HashMap<String,Object>();
        //业务层将po转化成vo
        map.put("currJson",productExtendService.productExtend2ProductExtendVo(productExtendService.findByCategoryId(categoryId)));
        return map ;
    }
}