package com.biz.soa.product.cloud.feign;

import com.biz.gbck.vo.product.promotion.TestPromotionVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by david-liu on 2017/04/27 10:12.
 */
@FeignClient("soa-promotion")
public interface PromotionFeignClient {

    @GetMapping(value = "/product/promotion/test")
    TestPromotionVo testPromotionVo();
}
