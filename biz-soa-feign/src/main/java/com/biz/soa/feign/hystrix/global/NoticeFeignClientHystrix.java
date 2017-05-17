package com.biz.soa.feign.hystrix.global;

import com.biz.gbck.vo.info.ListNoticeAfterLastReqVo;
import com.biz.soa.feign.client.global.NoticeFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by dylan on 2017-5-16
 */
@Component
public class NoticeFeignClientHystrix implements NoticeFeignClient {


    @Override
    public JSONResult findUserNoticeAfter(@RequestBody ListNoticeAfterLastReqVo reqVo) {
        return null;
    }
}
