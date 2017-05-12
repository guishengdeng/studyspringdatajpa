package com.biz.pay.wechat.exceptions;

public class WechatResponseParseException extends RuntimeException {

	private static final long serialVersionUID = 2376541190734892910L;

	public WechatResponseParseException() {
    }

    public WechatResponseParseException(String message) {
        super(message);
    }

    public WechatResponseParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatResponseParseException(Throwable cause) {
        super(cause);
    }

    public WechatResponseParseException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
