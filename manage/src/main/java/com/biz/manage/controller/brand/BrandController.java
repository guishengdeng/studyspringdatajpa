package com.biz.manage.controller.brand;

import com.biz.core.page.PageResult;
import com.biz.gbck.vo.product.backend.IdNameVo;
import com.biz.gbck.vo.product.backend.SearchPageVo;
import com.biz.service.product.backend.BrandService;
import com.biz.service.product.backend.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xys on 2017/4/25.
 */
@Controller
@RequestMapping("product/brand")
public class BrandController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    /*@GetMapping
    @PreAuthorize("hasAuthority('OPT_BRAND_LIST')")
    public ModelAndView list(SearchPageVo reqVo, Long categoryId, Boolean showChild) {

        BootstrapTablePageResult<BrandListItemVo> brands = null;
        reqVo.setPageIndex(10);
        reqVo.setPageSize(20);

        try {
            brands = brandService.listBrands(reqVo, categoryId, showChild);
        } catch (IllegalParameterException | CategoryNotFoundException e){
            logger.error("获取分类数据出错", e.getMessage(), e);
        }

        return new ModelAndView("manage/brand/list").addObject("brands",brands);
    }*/

    @GetMapping
    @PreAuthorize("hasAuthority('OPT_BRAND_LIST')")
    public ModelAndView list(SearchPageVo reqVo) {
        reqVo.setPageIndex(10);
        reqVo.setPageSize(20);

        PageResult<IdNameVo> brands = brandService.findBrandsByName(reqVo);

        return new ModelAndView("manage/brand/list").addObject("brands",brands);
    }
}
