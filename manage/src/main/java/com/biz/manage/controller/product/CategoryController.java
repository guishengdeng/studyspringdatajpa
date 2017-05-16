package com.biz.manage.controller.product;

import com.biz.core.util.StringUtil;
import com.biz.gbck.exceptions.product.CategoryNotFoundException;
import com.biz.gbck.vo.product.backend.*;
import com.biz.manage.controller.BaseController;
import com.biz.service.product.backend.CategoryService;
import com.biz.service.product.backend.ProductExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author yangzichun
 * @date 2017/4/25
 */
@Controller
@RequestMapping("manage/categories")
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductExtendService productExtendService;
    @GetMapping
    public ModelAndView list(String id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("manage/product/category/list");
        List<CategoryListItemVo> categories;
        if (StringUtil.isNullOrEmpty(id)) {
            categories = categoryService.listCategories();
        } else {
            categories = categoryService.listCategories(Long.parseLong(id));
        }
        session.setAttribute("categories", categories);
        return modelAndView;
    }

    @RequestMapping("/treeView.do")
    public @ResponseBody
    List<CategoryTreeViewVo> treeView() {
        return categoryService.getCategoryTreeViewVos();
    }


    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("manage/product/category/addOrUpdate");
    }

    @RequestMapping("/create.do")
    public String create(CreateCategoryVo createCategoryVo) throws CategoryNotFoundException {
        categoryService.createCategory(createCategoryVo);
        return "redirect:/manage/categories.do";
    }

    @RequestMapping("/update.do")
    public String update(UpdateCategoryVo updateCategoryVo) throws CategoryNotFoundException {
        categoryService.updateCategory(updateCategoryVo);
        return "redirect:/manage/categories.do";
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Boolean delete(@RequestParam("id") Long id) {
        try {
            return categoryService.deleteCategory(id);
        } catch (Exception e) {
            logger.info("delete category[{}] failed.", id, e);
            return false;
        }
    }

    @RequestMapping("{id}")
    public ModelAndView detail(@PathVariable Long id) {
        CategoryRespVo respVo = categoryService.findById(id);
        return new ModelAndView("manage/product/category/addOrUpdate").addObject("category", respVo);
    }

    /**
     * 根据分类id查询当前分类下所有的扩展属性
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("{id}")
    @PreAuthorize("hasAuthority('OPT_CATEGORY_DETAIL')")
    public String  detail(@PathVariable Long id, Model model){
        model.addAttribute("currentProductExtend",productExtendService.findByCategoryId(id));
        model.addAttribute("categoryId",id);
        return "manage/productextend/list";
    }
}
