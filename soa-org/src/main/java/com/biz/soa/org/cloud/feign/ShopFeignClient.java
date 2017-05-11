package com.biz.soa.org.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: liubin
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-shop")
public class ShopFeignClient {


}
