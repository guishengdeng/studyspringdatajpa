package com.biz.manage.controller.product;

import com.biz.gbck.vo.product.backend.BootstrapTablePageResult;
import com.biz.gbck.vo.product.backend.ProductFilterListItemVO;
import com.biz.gbck.vo.product.backend.ProductFilterListReqVO;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.manage.controller.BaseController;
import com.biz.soa.feign.client.product.ProductFeignClient;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by david-liu on 2017/05/05 09:31.
 */
@RestController
@RequestMapping(value = "/manage/product/filter")
public class ProductFilterController extends BaseController {

    @Autowired
    private ProductFeignClient productFeignClient;

    @GetMapping(value = "/list")
    public ModelAndView listProductFilters(ProductFilterListReqVO reqVO) throws Exception {
        MicroServiceResult<BootstrapTablePageResult<ProductFilterListItemVO>> microServiceResult = productFeignClient.getProductFilters(reqVO);
        if (microServiceResult.isSuccess()) {
            BootstrapTablePageResult<ProductFilterListItemVO> result = microServiceResult.getData();
        } else {
            throw microServiceResult.getException();
        }
        return null;
    }

    @GetMapping(value = "/new")
    public ModelAndView toAddFilterPage(@RequestParam("categoryId") Long categoryId) {
        Preconditions.checkArgument(categoryId != null, "请先选择分类");
        return new ModelAndView("manage/product/filter/add");
    }


}
