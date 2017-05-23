package com.biz.soa.feign.client.global;

import com.biz.gbck.vo.info.ListNoticeAfterLastReqVo;
import com.biz.soa.feign.hystrix.global.NoticeFeignClientHystrix;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.notify.NotifyVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: dylan 消息中心
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-org", fallback = NoticeFeignClientHystrix.class)
public interface NoticeFeignClient {

    @RequestMapping(value = "soa/info/notices", method = RequestMethod.POST)
    JSONResult findUserNoticeAfter(@RequestBody ListNoticeAfterLastReqVo reqVo);

    /**
     * manage给对应用户发送消息
     * @param name 后台登录用户
     * @param notifyVo 消息参数
     */
    @RequestMapping(value = "soa/info/sendNotification", method = RequestMethod.POST)
    void sendNotification(@RequestParam("name")String name, @RequestBody  NotifyVo notifyVo);
}
