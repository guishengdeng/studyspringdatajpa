package com.biz.exception;

import com.biz.gbck.common.exception.CommonRuntimeException;

/**
 * Created by defei on 3/16/16.
 */
public class SMSException extends CommonRuntimeException {

    public SMSException() {
        super();
    }

    public SMSException(String message) {
        super(message);
    }

    public SMSException(String message, int code) {
        super(message, code);
    }

    public SMSException(Throwable cause) {
        super(cause);
    }

    public SMSException(Throwable cause, int code) {
        super(cause, code);
    }

    public SMSException(String message, Throwable cause) {
        super(message, cause);
    }

    public SMSException(String message, int code, Throwable cause) {
        super(message, code, cause);
    }

    public SMSException(String message, Throwable cause, boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
