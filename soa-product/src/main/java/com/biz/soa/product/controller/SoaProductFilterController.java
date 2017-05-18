package com.biz.soa.product.controller;

import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.exceptions.product.ProductFilterNotFoundException;
import com.biz.gbck.vo.product.ProductFilterListItemVO;
import com.biz.gbck.vo.product.backend.BootstrapTablePageResult;
import com.biz.gbck.vo.product.backend.ProductFilterListReqVO;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.service.product.backend.ProductFilterService;
import com.biz.soa.base.SoaBaseController;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by david-liu on 2017/05/05 16:17.
 */
@RestController
@RequestMapping(value = "/soa/product/filter")
public class SoaProductFilterController extends SoaBaseController {

    @Autowired
    private ProductFilterService productFilterService;

    @PostMapping(value = "/list")
    public MicroServiceResult<BootstrapTablePageResult<ProductFilterListItemVO>> listProductFilters(ProductFilterListReqVO reqVO) {
        try {
            BootstrapTablePageResult<ProductFilterListItemVO> bootstrapTablePageResult = productFilterService.listProductFilters(reqVO.getSearchPageVo(), reqVO.getCategoryId());
            return this.render200(bootstrapTablePageResult);
        } catch (IllegalParameterException | ProductFilterNotFoundException e) {
            return render500(e);
        }
    }

    @GetMapping(value = "/app/searchResult")
    public MicroServiceResult<List<ProductFilterListItemVO>> getAppProductSearchResultFilters() {
        return this.render200(Lists.newArrayList());
    }
}
