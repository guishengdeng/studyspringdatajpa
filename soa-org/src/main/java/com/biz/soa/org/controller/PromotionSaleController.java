package com.biz.soa.org.controller;

import com.biz.gbck.vo.product.gbck.response.ProductAppListItemPromotionVo;
import com.biz.gbck.vo.product.promotion.TestPromotionVo;
import com.biz.gbck.vo.product.promotion.soa.singleProduct.request.ProductSimpleSpecialOfferReqVo;
import com.biz.gbck.vo.product.promotion.soa.singleProduct.request.ProductSingleProductPromotionReqVo;
import com.biz.gbck.vo.product.promotion.soa.singleProduct.request.ProductsSimpleSpecialOfferReqVo;
import com.biz.gbck.vo.product.promotion.soa.singleProduct.response.ProductPromotionCutRespVo;
import com.biz.gbck.vo.product.promotion.soa.singleProduct.response.ProductSimpleSpecialOfferRespVo;
import com.biz.gbck.vo.product.promotion.soa.singleProduct.response.ProductSingleProductPromotionRespVo;
import com.biz.gbck.vo.product.promotion.soa.singleProduct.response.ProductsSimpleSpecialOfferRespVo;
import com.biz.soa.base.SoaBaseController;
import com.biz.gbck.vo.soa.MicroServiceResult;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/singleProduct/list")
    public ProductSingleProductPromotionRespVo singleProductPromotionList(ProductSingleProductPromotionReqVo reqVo) {
        return new ProductSingleProductPromotionRespVo();
    }

    @PostMapping(value = "/simpleSpecialOffer/list")
    public ProductsSimpleSpecialOfferRespVo simpleSpecialOfferPromotions(ProductsSimpleSpecialOfferReqVo reqVo) {
        return new ProductsSimpleSpecialOfferRespVo();
    }

    @PostMapping(value = "/simpleSpecialOffer")
    public ProductSimpleSpecialOfferRespVo simpleSpecialOfferPromotion(ProductSimpleSpecialOfferReqVo reqVo) {
        return new ProductSimpleSpecialOfferRespVo();
    }

    @PostMapping(value = "/computePromotionCut")
    public ProductPromotionCutRespVo promotionCut() {
        return null;
    }

    @PostMapping(value = "/appProductListPromotions")
    public MicroServiceResult<List<ProductAppListItemPromotionVo>> productPromotions() {
        return null;
    }

}