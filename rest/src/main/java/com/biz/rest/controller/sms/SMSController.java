package com.biz.rest.controller.sms;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.DepotnearbyExceptionFactory;
import com.biz.gbck.vo.sms.SMSSentReqVo;
import com.biz.gbck.vo.sms.SMSValidateReqVo;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.util.RestUtil;
import com.biz.soa.feign.client.sms.SMSFeignClient;
import com.biz.support.web.handler.JSONResult;
import com.biz.support.web.util.HttpServletHelper;
import org.codelogger.utils.MathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 短信验证码服务
 * Created by dylan on 2017-5-16
 */
@RestController
@RequestMapping("sms") public class SMSController extends BaseRestController {


    @Autowired
    private SMSFeignClient smsFeignClient;

    private static Logger logger = LoggerFactory.getLogger(SMSController.class);
    /**
     * 发送验证码短信
     */
    @RequestMapping(value = "send", method = RequestMethod.POST) public JSONResult sendSMS(
        HttpServletRequest request) throws CommonException {
        String clientIP = HttpServletHelper.getClientIP(request);
        SMSSentReqVo smsSentReqVo = RestUtil.parseBizData(request, SMSSentReqVo.class);
        smsSentReqVo.setClientIP(clientIP);
        smsFeignClient.sendSMS(smsSentReqVo);
        return new JSONResult();
    }

    /**
     * 验证短信验证码是否正确, 不会让验码失效
     */
    @RequestMapping(value = "validate", method = RequestMethod.POST) public JSONResult validate(
        HttpServletRequest request) throws CommonException {
        String clientIP = HttpServletHelper.getClientIP(request);
        SMSValidateReqVo reqVo = RestUtil.parseBizData(request, SMSValidateReqVo.class);
        reqVo.setClientIP(clientIP);
        Boolean valid =
                smsFeignClient.validateSMSCode(reqVo);
        CommonException commonException = DepotnearbyExceptionFactory.SMS.INVALID_SMS_CODE;
        return valid ?
            new JSONResult() :
            new JSONResult(commonException.getCode(), commonException.getMessage());
    }





}
