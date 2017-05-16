package com.biz.soa.org.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.gbck.common.com.AlidayuTemplateCode;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.DepotnearbyExceptionFactory;
import com.biz.gbck.vo.sms.SMSSentReqVo;
import com.biz.gbck.vo.sms.SMSValidateReqVo;
import com.biz.soa.org.service.interfaces.SmsSoaService;
import com.biz.support.web.handler.JSONResult;
import org.codelogger.utils.MathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 短信验证码服务
 * Created by dylan on 2017-5-16
 */
@RestController
@RequestMapping("sms") public class SMSController extends BaseRestController {

    @Autowired
    private SmsSoaService smsSoaService;

    private static Logger logger = LoggerFactory.getLogger(SMSController.class);
    /**
     * 发送验证码短信
     */
    @RequestMapping(value = "send", method = RequestMethod.POST)
    public JSONResult sendSMS(@RequestBody SMSSentReqVo smsSentReqVo, HttpServletRequest request) throws CommonException {

        logger.info("Received /sms/sent POST request with mobile:{}, type:{} from ip:{}.",
            smsSentReqVo.getMobile(), smsSentReqVo.getAction(), smsSentReqVo.getClientIP());
        String smsCode = generateNewAuthCode(6);
        JSONObject params = new JSONObject();
        params.put("code", smsCode);
        params.put("action", smsSentReqVo.getAction().getDescription());
        smsSoaService
            .sendSMS(smsSentReqVo.getMobile(), smsSentReqVo.getAction(), smsCode,
                AlidayuTemplateCode.SMS_CODE, params.toJSONString(), smsSentReqVo.getClientIP());
        return new JSONResult();
    }

    /**
     * 验证短信验证码是否正确, 不会让验码失效
     */
    @RequestMapping(value = "validate", method = RequestMethod.POST) public JSONResult validate(
            @RequestBody SMSValidateReqVo reqVo, HttpServletRequest request) throws CommonException {

        logger.info("Received /sms/validate POST request with mobile:{}, type:{} from IP[{}].",
            reqVo.getMobile(), reqVo.getAction(), reqVo.getClientIP());
        Boolean valid =
                smsSoaService.validateSMSCode(reqVo.getMobile(), reqVo.getAction(), reqVo.getSmsCode());
        CommonException commonException = DepotnearbyExceptionFactory.SMS.INVALID_SMS_CODE;
        return valid ?
            new JSONResult() :
            new JSONResult(commonException.getCode(), commonException.getMessage());
    }

    private String generateNewAuthCode(Integer size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(MathUtils.randomInt(0, 9));
        }
        return sb.toString();
    }




}
