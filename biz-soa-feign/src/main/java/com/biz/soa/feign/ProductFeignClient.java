package com.biz.soa.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by david-liu on 2017/05/04 14:30.
 */
@FeignClient(name = "depotnextdoor-product")
public interface ProductFeignClient {

    @RequestMapping(value = "/soa/product/test", method = RequestMethod.GET)
    String getTestString();

}
