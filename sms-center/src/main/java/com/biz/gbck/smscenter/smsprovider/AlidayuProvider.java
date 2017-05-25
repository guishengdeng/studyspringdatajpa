package com.biz.gbck.smscenter.smsprovider;

import com.biz.gbck.smscenter.common.CommonConstant;
import com.biz.gbck.smscenter.common.Message;
import com.biz.gbck.smscenter.common.SendResult;
import com.biz.gbck.smscenter.common.SmsQueryResult;
import com.biz.gbck.smscenter.interfaces.SmsProvider;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Function: Alidayu short message service provider
 * Created by david-liu on 3/11/16.
 */
@Component("alidayuProvider")
public class AlidayuProvider extends SmsProvider {

    public static final String alidayuServiceError [] = new String[] {
            "isv.OUT_OF_SERVICE",
            "isv.PRODUCT_UNSUBSCRIBE",
            "isv.ACCOUNT_NOT_EXISTS",
            "isv.ACCOUNT_ABNORMAL"
    };

    public static final String alidayuSmsParamError [] = new String[] {
            "isv.SMS_TEMPLATE_ILLEGAL",
            "isv.SMS_SIGNATURE_ILLEGAL",
            "isv.MOBILE_NUMBER_ILLEGAL",
            "isv.MOBILE_COUNT_OVER_LIMIT",
            "isv.TEMPLATE_MISSING_PARAMETERS",
            "isv.INVALID_PARAMETERS",
            "isv.BUSINESS_LIMIT_CONTROL",
            "isv.INVALID_JSON_PARAM"
    };

    private static final Logger logger = LoggerFactory.getLogger(AlidayuProvider.class);

    @Autowired
    @Qualifier("taobaoClient")
    private TaobaoClient taobaoClient;

    @Autowired
    private AlibabaAliqinFcSmsNumSendRequest smsSendRequest;

    @Override
    public SendResult sendNotice(Message message) {
        logger.info("尝试使用阿里大鱼发送短信");
        smsSendRequest.setSmsType(CommonConstant.SMS_TYPE);
        smsSendRequest.setSmsFreeSignName(CommonConstant.SMS_SIGNATURE);
        smsSendRequest.setSmsParam(message.getAlidayuTemplateParams());
        smsSendRequest.setRecNum(message.getDestination());
        smsSendRequest.setSmsTemplateCode(message.getAlidayuTemlateCode());

        AlibabaAliqinFcSmsNumSendResponse response = null;
        try {
            response = taobaoClient.execute(smsSendRequest);
            if (response.getResult().getSuccess()) {
                return new SendResult(CommonConstant.SmsResultCode.API_SUCCESS, response.getSubCode());
            }
        } catch(ApiException e) {
            logger.error("使用阿里大鱼API出错, 错误信息[{}]", e.getMessage());
            return new SendResult(CommonConstant.SmsResultCode.API_ERROR, e.getMessage());
        } catch(Exception e) {
            logger.error("使用阿里大鱼API出错, 错误信息[{}]", response.getBody());
            return new SendResult(CommonConstant.SmsResultCode.UNKNOWN_ERROR, response.getSubCode());
        }
        if(StringUtils.equals(response.getErrorCode(), "0")) {
            logger.info("短信已由阿里大鱼发送至[{}]", message.getDestination());
            return new SendResult(CommonConstant.SmsResultCode.API_SUCCESS, "");
        } else if(ArrayUtils.contains(alidayuServiceError, response.getSubCode())) {
            logger.warn("阿里大鱼短信服务配置异常");
            return new SendResult(CommonConstant.SmsResultCode.SERVICE_ERROR, response.getSubCode());
        } else if(ArrayUtils.contains(alidayuSmsParamError, response.getSubCode())) {
            logger.warn("阿里大鱼调用短信服务参数异常");
            return new SendResult(CommonConstant.SmsResultCode.SMS_PARAM_ERROR, response.getSubCode());
        } else {
            logger.warn("阿里大鱼服务未知异常");
            return new SendResult(CommonConstant.SmsResultCode.UNKNOWN_ERROR, response.getSubCode());
        }
    }

    @Override
    public SmsQueryResult queryStatus() {
        return null;
    }

}
