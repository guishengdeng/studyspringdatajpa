package com.biz.soa.feign.hystrix.global;


import com.biz.soa.feign.client.global.GlobalFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * Created by david-liu on 2017/05/12 12:19.
 */
@Component
public class GlobalFeignClientHystrix implements GlobalFeignClient {

    @Override
    public JSONResult getAppConfigMap() {
        return null;
    }

    @Override
    public JSONResult needUpgrade(@PathVariable("os") String os, @PathVariable("ver") String ver, @PathVariable("inhourse") String inhourse) {
        return null;
    }
}
