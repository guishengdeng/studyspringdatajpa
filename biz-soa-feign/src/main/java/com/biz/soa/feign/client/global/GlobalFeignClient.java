package com.biz.soa.feign.client.global;

import com.biz.soa.feign.hystrix.global.GlobalFeignClientHystrix;
import com.biz.support.web.handler.JSONResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @author: dylan 启动上报
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-org", fallback = GlobalFeignClientHystrix.class)
public interface GlobalFeignClient {
    /**
     * 获取启动上报返回参数
     */
    @RequestMapping(value = "/init/init")
    JSONResult getAppConfigMap();

    /**
     * 获取升级配置
     * @param os 型号 ios android
     * @param ver 现在的版本号
     * @param inhourse 是否强制升级
     * @return
     */
    @RequestMapping(value = "/init/upgrade")
    JSONResult needUpgrade(@RequestParam("os")String os, @RequestParam("ver")String ver,
                           @RequestParam("inhourse")String inhourse);
}
