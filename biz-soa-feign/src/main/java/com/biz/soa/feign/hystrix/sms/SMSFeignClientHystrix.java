package com.biz.soa.feign.hystrix.sms;


import com.biz.gbck.vo.sms.SMSSentReqVo;
import com.biz.gbck.vo.sms.SMSValidateReqVo;
import com.biz.soa.feign.client.sms.SMSFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by david-liu on 2017/05/12 12:19.
 */
@Component
public class SMSFeignClientHystrix implements SMSFeignClient {


    @Override
    public void sendSMS(@RequestBody SMSSentReqVo smsSentReqVo) {

    }

    @Override
    public Boolean validateSMSCode(@RequestBody SMSValidateReqVo reqVo) {
        return null;
    }
}
