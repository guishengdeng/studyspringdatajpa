package com.biz.soa.feign.client.product;

import com.biz.gbck.vo.order.resp.OrderPromotionReqVo;
import com.biz.gbck.vo.product.promotion.OrderPromotionRespVO;
import com.biz.gbck.vo.promotion.PromotionReqVo;
import com.biz.gbck.vo.promotion.PromotionRespAppVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by david-liu on 2017/04/27 10:12.
 */
@FeignClient("soa-promotion")
public interface PromotionFeignClient {

    @RequestMapping(value = "/product/promotion/order")
    MicroServiceResult<OrderPromotionRespVO> orderProductsPromotion(@RequestBody OrderPromotionReqVo reqVo);

    @RequestMapping(value = "/product/promotion/promotionsForProductId")
	List<PromotionRespAppVo> getUseablePromotionsForProductId(@RequestBody PromotionReqVo promotionReqVo);
}
