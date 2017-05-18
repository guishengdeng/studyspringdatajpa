package com.biz.manage.controller.category;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.product.meta.ExtendProperty;
import com.biz.gbck.dao.mysql.po.product.meta.ProductExtend;
import com.biz.service.product.backend.CategoryService;
import com.biz.service.product.backend.ProductExtendService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * CategoryController
 *
 * @author guisheng.deng
 * @date 2017年05月02日
 * @reviewer
 * @description
 * @see
 */
@Controller
@Secured("ROLE_CATEGORY")
@RequestMapping("product/categories")
public class CategoryControllers {

   /* @Autowired*/
    private CategoryService categoryService;
   /* @Autowired*/
    private ProductExtendService productExtendService;
    @GetMapping
    @PreAuthorize("hasAuthority('OPT_CATEGORY_LIST')")
    public ModelAndView  listCategory(HttpSession session){
        session.setAttribute("categories",categoryService.getCategoryTreeViewVos());
        return new ModelAndView("manage/productextend/list");
    }
    @RequestMapping("{id}")
    @PreAuthorize("hasAuthority('OPT_CATEGORY_DETAIL')")
    public String  detail(@PathVariable Long id, Model model,HttpSession session){
        JSONArray json = new JSONArray();
        if(productExtendService.findByCategoryId(id) != null){
            for(ProductExtend item : productExtendService.findByCategoryId(id)){
                json.add(JSONObject.fromObject("{\"name\":\""+item.getName()+"\",\"id\":\""+item.getId()+"\"}"));
            }
        }
        session.setAttribute("currJson",json);
        model.addAttribute("currentProductExtend",productExtendService.findByCategoryId(id));
        model.addAttribute("categoryId",id);
        return "manage/productextend/list";
    }
}