package com.biz.pay.wechat.exceptions;

public class SignIncorrectException extends RuntimeException {

	private static final long serialVersionUID = 3468792402215971165L;

	public SignIncorrectException() {
    }

    public SignIncorrectException(String message) {
        super(message);
    }

    public SignIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignIncorrectException(Throwable cause) {
        super(cause);
    }

    public SignIncorrectException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
