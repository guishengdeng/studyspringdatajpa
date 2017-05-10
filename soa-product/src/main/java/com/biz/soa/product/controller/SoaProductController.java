package com.biz.soa.product.controller;

import com.biz.gbck.vo.product.gbck.request.ProductAppDetailReqVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppListItemVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.base.SoaBaseController;
import com.biz.soa.product.cloud.feign.PromotionFeignClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/app/list")
    public MicroServiceResult<List<ProductAppListItemVo>> appProductList(ProductAppListReqVo reqVo) {
        return render200(null);
    }

    @GetMapping(value = "/app/detail")
    public MicroServiceResult<ProductAppDetailRespVO> appProductDetail(ProductAppDetailReqVo reqVo) {
        return render200(null);
    }

    @GetMapping(value = "/test")
    public String getTestString() {
        return "I am a test String";
    }

}