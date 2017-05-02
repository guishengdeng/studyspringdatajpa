package com.biz.gbck.common.com.setvice.mq;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.common.com.AlidayuTemplateCode;
import com.biz.gbck.common.com.util.SmsContentTemplate;
import com.biz.gbck.common.com.util.mq.MqMessage;

/**
 * Function: Message POJO
 * Created by david-liu on 3/11/16.
 */
public class Message {

    private String destination;

    private String messageBody;

    private String alidayuTemlateCode;

    private String alidayuTemplateParams;

    public Message() {

    }

    public Message(String destination, String messageBody) {
        this.messageBody = messageBody;
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getAlidayuTemlateCode() {
        return alidayuTemlateCode;
    }

    public void setAlidayuTemlateCode(String alidayuTemlateCode) {
        this.alidayuTemlateCode = alidayuTemlateCode;
    }

    public String getAlidayuTemplateParams() {
        return alidayuTemplateParams;
    }

    public void setAlidayuTemplateParams(String alidayuTemplateParams) {
        this.alidayuTemplateParams = alidayuTemplateParams;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSON(new MqMessage.Builder().setMessageDestination("15108426725")
            .setMessageBody(new SmsContentTemplate.Builder()
                .setAlidayuTemplateCode(AlidayuTemplateCode.SYNC_ORDER_TO_OCS_SUCCESS)
                .build())
            .setAlidayuTemplateCode(AlidayuTemplateCode.SYNC_ORDER_TO_OCS_SUCCESS.getTemplateCode())
            .build()));
    }
}
