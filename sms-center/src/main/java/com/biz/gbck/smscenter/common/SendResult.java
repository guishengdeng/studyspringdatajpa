package com.biz.gbck.smscenter.common;

/**
 * Function: Record message send result
 * Created by david-liu on 3/11/16.
 */
public class SendResult {

    private CommonConstant.SmsResultCode code;

    private String message;

    public SendResult(CommonConstant.SmsResultCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonConstant.SmsResultCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
