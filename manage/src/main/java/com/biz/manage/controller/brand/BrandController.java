package com.biz.manage.controller.brand;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.exceptions.product.CategoryNotFoundException;
import com.biz.gbck.vo.advertisement.frontend.request.AdvertisementRequestVo;
import com.biz.gbck.vo.product.backend.BrandListItemVo;
import com.biz.gbck.vo.product.backend.CategoryItemVo;
import com.biz.gbck.vo.product.backend.CreateBrandVo;
import com.biz.gbck.vo.product.backend.UpdateBrandVo;
import com.biz.service.product.backend.BrandService;
import com.biz.service.product.backend.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

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
        SearchPageVo searchPageVo = new SearchPageVo();
        searchPageVo.setPageSize(10);
        searchPageVo.setPageIndex(10);
        categoryId = 1l;
        showChild = false;
        searchPageVo.setSearchValue("1");
        try {
            logger.debug("requset param req [{}],categoryId [{}] ,showChild [{}]", JSON.toJSONString(reqVo), categoryId, showChild);
            BootstrapTablePageResult<BrandListItemVo> result = brandService.listBrands(searchPageVo, categoryId, showChild);
            return new ModelAndView("manage/brand/list","brands",result);
        } catch (IllegalParameterException | CategoryNotFoundException e) {
            logger.error("获取分类数据出错", e.getMessage(), e);
            return null;
        }
    }*/

    @GetMapping
    @PreAuthorize("hasAuthority('OPT_BRAND_LIST')")
    public ModelAndView tolist() {
        List<BrandListItemVo> brands = brandService.findBrands();
        return new ModelAndView("manage/brand/list", "brands", brands);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView add(String isNew, String brandId, String categoryId) {
        logger.debug("in BrandController add isNew=[{}],brandId=[{}],categoryId=[{}]", isNew, brandId, categoryId);
        ModelAndView mav = new ModelAndView("manage/brand/add");

        if (Objects.equals(isNew, Boolean.TRUE.toString())) {
            //新增品牌
            mav.addObject("isNew", Boolean.TRUE.toString());
            mav.addObject("categoryId", categoryId);
            CategoryItemVo categoryItemVo = categoryService.getCategoryItem(Long.valueOf(categoryId));
            mav.addObject("categoryName", categoryItemVo.getName());
        } else if (Objects.equals(isNew, Boolean.FALSE.toString())) {
            //分类不可修改
            UpdateBrandVo updateBrandVo = brandService.getUpdateBrandVo(Long.valueOf(brandId));
            mav.addObject("isNew", Boolean.FALSE.toString());
            mav.addObject("updateBrandVo", updateBrandVo);
            mav.addObject("categoryId", updateBrandVo.getCategoryId());
            CategoryItemVo categoryItemVo = categoryService.getCategoryItem(updateBrandVo.getCategoryId());
            mav.addObject("categoryName", categoryItemVo.getName());
        }
        return mav;
    }

    @PostMapping("save")
    @PreAuthorize("hasAuthority('OPT_BRAND_CREATE')")
    public ModelAndView create(CreateBrandVo brandVo) {
        brandVo.setCategoryId(1L);
        brandVo.setStatus(CommonStatusEnum.ENABLE);
        try {
            brandService.createBrand(brandVo);
        } catch (CategoryNotFoundException e) {
            logger.error("新增品牌失败", e.getMessage(), e);
        }

        return new ModelAndView("redirect:/product/brand.do");
    }

}
