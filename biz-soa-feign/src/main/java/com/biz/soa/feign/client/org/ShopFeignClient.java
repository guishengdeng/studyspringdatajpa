package com.biz.soa.feign.client.org;

import com.biz.soa.feign.hystrix.org.ShopFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author: liubin
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-org", fallback = ShopFeignClientHystrix.class)
public interface ShopFeignClient {

}
