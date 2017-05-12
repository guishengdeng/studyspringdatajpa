package com.biz.pay.wechat.exceptions;

public class MissingResourceException extends RuntimeException {

	private static final long serialVersionUID = 6885049707300274177L;

	public MissingResourceException() {
    }

    public MissingResourceException(String message) {
        super(message);
    }

    public MissingResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingResourceException(Throwable cause) {
        super(cause);
    }

    public MissingResourceException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
