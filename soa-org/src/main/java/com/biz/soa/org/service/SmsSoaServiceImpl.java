package com.biz.soa.org.service;

import com.alibaba.fastjson.JSONObject;
import com.biz.core.util.StringTool;
import com.biz.exception.SMSException;
import com.biz.gbck.common.com.AlidayuTemplateCode;
import com.biz.gbck.common.com.SMSType;
import com.biz.gbck.common.com.util.SmsContentTemplate;
import com.biz.gbck.common.com.util.mq.MqMessage;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.DepotnearbyExceptionFactory;
import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.redis.repository.sms.SMSRedisDao;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.dao.redis.ro.sms.SMSRo;
import com.biz.message.MessageService;
import com.biz.message.SimpleBizMessage;
import com.biz.message.queue.BizBaseQueue;
import com.biz.service.CommonService;
import com.biz.soa.org.service.interfaces.ShopSoaService;
import com.biz.soa.org.service.interfaces.SmsSoaService;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.biz.soa.org.service.interfaces.UserSoaService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * 短信服务
 * Created by defei on 3/16/16.
 */
//@PropertySources({
//        @PropertySource(value = "classpath:application.properties")
//})
@Service
public class SmsSoaServiceImpl extends CommonService implements SmsSoaService {

    @Autowired
    private UserSoaService userSoaService;


    @Autowired
    private SMSRedisDao smsRedisDao;

    @Autowired
    private ShopSoaService shopSoaService;

   @Autowired
    private MessageService messageService;



    /**
     * 发送短信
     *
     * @param mobile    手机号
     * @param smsType   短信类型
     * @param smsCode   验证码
     * @param requestIp 客户端地址
     */
    public void sendSMS(String mobile, SMSType smsType, String smsCode,
                        AlidayuTemplateCode templateCode, String templateParam, String requestIp)
            throws SMSException, CommonException {

        //validateSMSParameters(mobile, smsType, smsCode);
        logger.debug("Send {} sms code to {}", smsType, mobile);

        UserRo user = userSoaService.findUserByMobile(mobile);
        if (smsType == SMSType.REGISTER || smsType == SMSType.CHANGE_MOBILE) {
            if (user != null) {
                throw DepotnearbyExceptionFactory.SMS.USER_EXIST;
            }
        } else if (smsType == SMSType.FORGOT_PASSWORD) {
            if (user == null) {
                throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
            }
        }

//        SecurityRespVo securityRespVoForIP =
//            securityService.recordBy(requestIp, (int) TimeUnit.MINUTES.toSeconds(10));
//        if (securityRespVoForIP.getVisitCountBeforeNow(TimeUnit.HOURS, 1) > limitTimes) {
//            throw new CommonException("获取验证码次数达到上限，请稍后尝试", ExceptionCode.Global.INFO_TO_USER);
//        }
//
//        SecurityRespVo securityRespVoForMobile =
//            securityService.recordBy(mobile, (int) TimeUnit.HOURS.toSeconds(10));
//        if (securityRespVoForMobile.getVisitCountBeforeNow(TimeUnit.HOURS, 1) > limitTimes) {
//            throw new CommonException("获取验证码次数达到上限，请稍后尝试", ExceptionCode.Global.INFO_TO_USER);
//        }

        ShopRo shopRo = user == null ? null : shopSoaService.findShopByUserId(Long.parseLong(user.getId()));


        SMSRo smsRo = smsRedisDao.findSMSCode(mobile, smsType);
        if (smsRo != null) {
            if (TimeUnit.SECONDS.toMillis(sendDurationInSecond) > System.currentTimeMillis() - smsRo
                    .getCreateTime()) {
                throw new CommonException(format("%s秒内不能重复获取", sendDurationInSecond),
                        ExceptionCode.Global.SMS_SENDTIME_INVALID);
            }
        }

        smsRedisDao.saveSMSCodeAndAutoExpire(mobile, smsType, smsCode, validTimeInSecond);

        try {
            String username = shopRo == null ? null : shopRo.getName();
            com.biz.gbck.common.com.setvice.mq.Message smsMessage =
                    new MqMessage.Builder().setMessageDestination(mobile).setMessageBody(
                            new SmsContentTemplate.Builder().setSmsType(smsType).setSmsCode(smsCode)
                                    .setUsername(username).setAlidayuTemplateCode(templateCode).build())
                            .setAlidayuTemplateCode(templateCode.getTemplateCode())
                            .setAlidayuTemplateParams(templateParam).build();
           messageService.sendMessage(BizBaseQueue.SMS_QUEUE, SimpleBizMessage.newMessage(smsMessage));
        } catch (Exception e) {
            throw new CommonException("获取验证码失败", ExceptionCode.Global.INFO_TO_USER);
        }
    }

    /**
     * 校验短信验证码是否正确,如果正确则使该短信验证码失效
     *
     * @param mobile  手机号
     * @param smsType 短信类型
     * @param smsCode 验证码
     */
    public Boolean validateAndDisableSMSCode(String mobile, SMSType smsType, String smsCode)
            throws CommonException {

        validateSMSParameters(mobile, smsType, smsCode);
        boolean validSMSCode = validateSMSCode(mobile, smsType, smsCode);
        if (validSMSCode) {
            logger.debug("delete {} smsCode for mobile[{}]", smsType, smsCode, mobile);
            smsRedisDao.deleteSMSCode(mobile, smsType);
        }
        return validSMSCode;
    }

    /**
     * 校验短信验证码是否正确,并使短信验证码继续有效
     *
     * @param mobile  手机号
     * @param smsType 短信类型
     * @param smsCode 验证码
     */
    public Boolean validateSMSCode(String mobile, SMSType smsType, String smsCode)
            throws CommonException {
        // todo liubin
//        validateSMSParameters(mobile, smsType, smsCode);
//        logger.debug("validate {} smsCode[{}] for mobile[{}]", smsType, smsCode, mobile);
//        SMSRo smsRo = smsRedisDao.findSMSCode(mobile, smsType);
//        return smsRo != null && smsCode.equalsIgnoreCase(smsRo.getCode());
        return true;
    }

    private void validateSMSParameters(String mobile, SMSType smsType, String smsCode)
            throws CommonException {
        if (!StringTool.isMobileValid(mobile)) {
            throw DepotnearbyExceptionFactory.SMS.ILLEGAL_MOBILE;
        }
        if (smsType == null || StringUtils.isBlank(smsCode)) {
            throw DepotnearbyExceptionFactory.SMS.INVALID_SMS_CODE;
        }
    }


    /**
     * 发送短信
     *
     * @param mobile       手机号
     * @param templateCode 短信模板编号
     */
    public void SMSMsg(String mobile, AlidayuTemplateCode templateCode, JSONObject templateParam)
            throws SMSException, CommonException {

        try {
            com.biz.gbck.common.com.setvice.mq.Message smsMessage;
            if (templateParam != null && templateParam.size() > 0) {
                smsMessage = new MqMessage.Builder().setMessageDestination(mobile).setMessageBody(
                        new SmsContentTemplate.Builder().setAlidayuTemplateCode(templateCode).setParams(templateParam)
                                .setUsername(templateParam.getString("username")).build())
                        .setAlidayuTemplateCode(templateCode.getTemplateCode())
                        .setAlidayuTemplateParams(templateParam.toJSONString()).build();
            } else {
                smsMessage = new MqMessage.Builder().setMessageDestination(mobile).setMessageBody(
                        new SmsContentTemplate.Builder().setAlidayuTemplateCode(templateCode).build())
                        .setAlidayuTemplateCode(templateCode.getTemplateCode()).build();
            }
            messageService.sendMessage(BizBaseQueue.MQ_SMS_CODE, SimpleBizMessage.newMessage(smsMessage));
        } catch (Exception e) {
            throw new CommonException("发送短信失败", ExceptionCode.Global.INFO_TO_USER);
        }

    }

    public void sendMessage2Users(List<UserPo> users, String templateCode, String content)
            throws CommonException {
        if (CollectionUtils.isEmpty(users)) {
            throw new CommonException("短信发送目标不存在");
        }

        for (UserPo user : users) {
            com.biz.gbck.common.com.setvice.mq.Message smsMessage =
                    new com.biz.gbck.common.com.setvice.mq.Message();
            smsMessage.setDestination(user.getMobile());
            smsMessage.setMessageBody(content);
            smsMessage.setAlidayuTemlateCode(templateCode);
            messageService.sendMessage(BizBaseQueue.SMS_QUEUE, SimpleBizMessage.newMessage(smsMessage));
        }
    }

    /**
     * 短信验证码有效时间,秒(默认30分钟)
     */
    @Value("${sms.validTimeInSecond:1800}")
    private int validTimeInSecond;

    /**
     * 短信验证码重发间隔时间,秒(默认1分钟)
     */
    @Value("${sms.sendDurationInSecond:60}")
    private int sendDurationInSecond;

    @Value("${sms.limitTimes:10}")
    private int limitTimes;


    private static final Logger logger = LoggerFactory.getLogger(SmsSoaServiceImpl.class);
}
