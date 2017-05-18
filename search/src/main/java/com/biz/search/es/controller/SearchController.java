package com.biz.search.es.controller;

import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.search.es.service.interfaces.ProductSearchService;
import com.biz.soa.base.SoaBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by david-liu on 2017/05/16 09:04.
 */
@RestController
@RequestMapping(value = "/search")
public class SearchController extends SoaBaseController {

    @Autowired
    private ProductSearchService productSearchService;

    @PostMapping(value = "/product")
    public MicroServiceResult<ProductSearchResultVo<ProductSearchResultEntityVo>> productSearch(@RequestBody ProductAppListReqVo reqVo) {
        try {
            return render200(productSearchService.searchProducts(reqVo));
        } catch (Exception e) {
            return render500(e);
        }
    }

}
