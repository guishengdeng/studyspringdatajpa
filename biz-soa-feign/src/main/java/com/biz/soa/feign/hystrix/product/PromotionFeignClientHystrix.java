package com.biz.soa.feign.hystrix.product;

import com.biz.gbck.vo.order.resp.OrderPromotionReqVo;
import com.biz.gbck.vo.product.promotion.OrderPromotionRespVO;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.feign.client.product.PromotionFeignClient;
import org.springframework.stereotype.Component;

/**
 * Created by david-liu on 2017/05/24 16:24.
 */
@Component
public class PromotionFeignClientHystrix implements PromotionFeignClient {
    @Override
    public MicroServiceResult<OrderPromotionRespVO> orderProductsPromotion(OrderPromotionReqVo reqVo) {
        return new MicroServiceResult<>();
    }
}