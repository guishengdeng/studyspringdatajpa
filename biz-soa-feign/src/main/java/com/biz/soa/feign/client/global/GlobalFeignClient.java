package com.biz.soa.feign.client.global;

import com.biz.soa.feign.hystrix.org.ShopFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


/**
 * @author: dylan 启动上报
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-org", fallback = ShopFeignClientHystrix.class)
public interface GlobalFeignClient {
    /**
     * 获取启动上报返回参数
     */
    @RequestMapping(value = "/init/init", method = RequestMethod.POST, consumes = "application/json")
    Map getAppConfigMap();



}
