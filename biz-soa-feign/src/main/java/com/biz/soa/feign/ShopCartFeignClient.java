package com.biz.soa.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by david-liu on 2017/05/12 10:52.
 */
@FeignClient(name = "depotnextdoor-order")
public interface ShopCartFeignClient {
}
