package com.biz.gbck.smscenter.smsprovider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biz.gbck.smscenter.common.CommonConstant;
import com.biz.gbck.smscenter.common.Message;
import com.biz.gbck.smscenter.common.SendResult;
import com.biz.gbck.smscenter.common.SmsQueryResult;
import com.biz.gbck.smscenter.interfaces.SmsProvider;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.codelogger.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Function: Yunpian short message provider
 * Created by david-liu on 3/11/16.
 */
@Component
public class YunpianProvider extends SmsProvider {

    private static final Logger logger = LoggerFactory.getLogger(YunpianProvider.class);

    private static final String yunpianSmsSendUrl = "https://sms.yunpian.com/v1/sms/send.json";

    @Value("${sms.yunpian.apikey}") private String appKey;

    @Override
    public SendResult sendNotice(Message message) {
        logger.info("尝试使用云片网发送短信");
        Map<String, String> paramsMap = Maps.newHashMap();
        paramsMap.put("apikey", appKey);
        paramsMap.put("mobile", message.getDestination());
        String text;
        if (message.getMessageBody().contains("【隔壁仓库】")) {
            text = message.getMessageBody();
        } else {
            text = "【隔壁仓库】".concat(message.getMessageBody());
        }
        paramsMap.put("text", text);
        String responseJson = HttpUtils.doPost(yunpianSmsSendUrl, paramsMap);
        JSONObject response = JSON.parseObject(responseJson);
        if (StringUtils.equals(response.get("code").toString(), "0")) {
            logger.info("短信已由云片网发送至[{}]", message.getDestination());
            return new SendResult(CommonConstant.SmsResultCode.API_SUCCESS, "");
        } else {
            logger.warn("云片网发送短信失败, 错误信息[{}]", response.get("msg"));
            return new SendResult(CommonConstant.SmsResultCode.API_ERROR,
                response.get("msg").toString());
        }
    }

    @Override
    public SmsQueryResult queryStatus() {
        return null;
    }

}
