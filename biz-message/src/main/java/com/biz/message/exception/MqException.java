package com.biz.message.exception;

/**
 * FileName: MqException
 * Description:
 * Author: david-liu
 * CreateTime: 2016-08-02 11:13
 */
public class MqException extends Throwable {
    public MqException() {
    }

    public MqException(String message) {
        super(message);
    }

    public MqException(String message, Throwable cause) {
        super(message, cause);
    }

    public MqException(Throwable cause) {
        super(cause);
    }

    public MqException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
