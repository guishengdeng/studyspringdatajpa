package com.biz.soa.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by david-liu on 2017/05/04 14:30.
 */
@FeignClient("depotnextdoor-product")
public interface ProductFeignClient {
}
