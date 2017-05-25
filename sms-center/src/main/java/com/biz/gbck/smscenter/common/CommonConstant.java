package com.biz.gbck.smscenter.common;

/**
 * Function: Recording common constant as static final variable
 * Created by david-liu on 3/11/16.
 */
public class CommonConstant {
    public static final String SMS_TYPE = "normal";

    public static final String SMS_SIGNATURE = "隔壁仓库";

    public static final String SMS_TEMPLATE_CODE = "SMS_7810200";

    public static final String DEFAULT_MAIL_SUBJECT = "隔壁仓库";

    public enum  SmsResultCode {
        API_SUCCESS,
        API_ERROR,
        SERVICE_ERROR,
        SMS_PARAM_ERROR,
        UNKNOWN_ERROR
    }
}
