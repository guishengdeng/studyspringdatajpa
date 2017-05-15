package com.biz.soa.product.controller;

import com.biz.gbck.vo.product.gbck.request.ProductAppDetailReqVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppListRespVO;
import com.biz.gbck.vo.search.IncrProductIdxReqVo;
import com.biz.gbck.vo.search.ProductIdxVO;
import com.biz.gbck.vo.search.TotalProductIdxReqVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.service.ProductIdxService;
import com.biz.service.product.frontend.ProductService;
import com.biz.soa.base.SoaBaseController;
import com.biz.soa.product.cloud.feign.PromotionFeignClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Soa Product Controller
 *
 * Created by david-liu on 2017/04/26 12:09.
 */
@RestController
@RequestMapping(value = "/soa/product")
public class SoaProductController extends SoaBaseController {

    @Autowired
    private PromotionFeignClient promotionClient;

    private ProductService productService;

    private ProductIdxService productIdxService;

    @PostMapping(value = "/app/list")
    public MicroServiceResult<ProductAppListRespVO> appProductList(@RequestBody ProductAppListReqVo reqVo) {
        try {
            return render200(productService.searchProducts(reqVo));
        } catch (IllegalArgumentException e) {
            return render500(e);
        }
    }

    @PostMapping(value = "/app/detail")
    public MicroServiceResult<ProductAppDetailRespVO> appProductDetail(@RequestBody ProductAppDetailReqVo reqVo) {
        try {
            return render200(productService.productDetail(reqVo));
        } catch (IllegalArgumentException e) {
            return render500(e);
        }
    }

    @PostMapping(value = "/search/totalIndices")
    public MicroServiceResult<List<ProductIdxVO>> totalProductIndices(@RequestBody TotalProductIdxReqVo reqVo) {
        try {
            return render200(productIdxService.getProductIndices(reqVo));
        } catch (IllegalArgumentException e) {
            return render500(e);
        }
    }

    @PostMapping(value = "/search/incrIndices")
    public MicroServiceResult<List<ProductIdxVO>> incrProductIndices(@RequestBody IncrProductIdxReqVo reqVo) {
        try {
            return render200(productIdxService.getProductIndices(reqVo));
        } catch (IllegalArgumentException e) {
            return render500(e);
        }
    }
}
