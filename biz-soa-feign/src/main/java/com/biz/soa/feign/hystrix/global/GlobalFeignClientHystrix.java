package com.biz.soa.feign.hystrix.global;


import com.biz.soa.feign.client.global.GlobalFeignClient;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by david-liu on 2017/05/12 12:19.
 */
@Component
public class GlobalFeignClientHystrix implements GlobalFeignClient {

    @Override
    public Map getAppConfigMap() {
        return null;
    }
}
