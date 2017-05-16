package com.biz.soa.feign.client.product;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by david-liu on 2017/05/16 00:13.
 */
@FeignClient(name = "depotnextdoor-search")
public interface ProductSearchFeignClient {
}
