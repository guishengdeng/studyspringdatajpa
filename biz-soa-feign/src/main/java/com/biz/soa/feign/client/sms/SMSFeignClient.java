package com.biz.soa.feign.client.sms;

import com.biz.gbck.vo.sms.SMSSentReqVo;
import com.biz.gbck.vo.sms.SMSValidateReqVo;
import com.biz.soa.feign.hystrix.sms.SMSFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author: dylan 启动上报
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-org", fallback = SMSFeignClientHystrix.class)
public interface SMSFeignClient {

    /**
     * 短信发送
     */
    @RequestMapping(value = "/sms/send")
    void sendSMS(@RequestBody SMSSentReqVo smsSentReqVo);

    /**
     * 验证短信验证码是否正确, 不会让验码失效
     * @return true 正确 false 不正确
     */
    @RequestMapping(value = "/sms/validate")
    Boolean validateSMSCode(@RequestBody SMSValidateReqVo reqVo);

}
