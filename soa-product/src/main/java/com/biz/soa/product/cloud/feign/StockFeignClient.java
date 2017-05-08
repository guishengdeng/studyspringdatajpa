package com.biz.soa.product.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by david-liu on 2017/05/04 09:46.
 */
@FeignClient("depotnextdoor-stock")
public interface StockFeignClient {
}
