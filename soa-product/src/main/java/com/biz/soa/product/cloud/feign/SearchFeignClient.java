package com.biz.soa.product.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by david-liu on 2017/05/04 09:44.
 */
@FeignClient("depotnextdoor-search")
public interface SearchFeignClient {
}
