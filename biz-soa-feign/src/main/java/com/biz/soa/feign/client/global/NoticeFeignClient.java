package com.biz.soa.feign.client.global;

import com.biz.gbck.vo.info.ListNoticeAfterLastReqVo;
import com.biz.soa.feign.hystrix.global.NoticeFeignClientHystrix;
import com.biz.support.web.handler.JSONResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: dylan 启动上报
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-org", fallback = NoticeFeignClientHystrix.class)
public interface NoticeFeignClient {

    @RequestMapping(value = "/info/notices")
    JSONResult findUserNoticeAfter(@RequestBody ListNoticeAfterLastReqVo reqVo);

}
