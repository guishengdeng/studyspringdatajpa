package com.biz.soa.promotion.controller;

import com.biz.gbck.vo.order.resp.OrderPromotionReqVo;
import com.biz.gbck.vo.product.promotion.OrderPromotionRespVO;
import com.biz.gbck.vo.product.promotion.TestPromotionVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.base.SoaBaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 商品促销Controller
 * Created by david-liu on 2017/04/24 16:13.
 */
@RestController
@RequestMapping(value = "/product/promotion")
public class PromotionSaleController extends SoaBaseController {

    private static final Logger logger = LoggerFactory.getLogger(PromotionSaleController.class);

    @GetMapping(value = "/test")
    public TestPromotionVo testPromotionVo() {
        TestPromotionVo testPromotionVo = new TestPromotionVo();
        testPromotionVo.setName("test");
        testPromotionVo.setMessage("hello spring cloud");
        return testPromotionVo;
    }

    @PostMapping(value = "order")
    public MicroServiceResult<OrderPromotionRespVO> orderProductsPromotion(@RequestBody OrderPromotionReqVo reqVo) {
        return new MicroServiceResult<>();
    }

}