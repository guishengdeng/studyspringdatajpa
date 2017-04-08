package com.depotnextdoor.core.exceptions;

/**
 * 所有需要捕获的异常的父类
 *
 * @author yanweijin
 * @date 2016年7月28日
 * @reviewer
 */
public class BizSystemException extends Exception {

    private static final long serialVersionUID = 6994564415657921473L;

    public BizSystemException() {
        super();
    }

    public BizSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BizSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizSystemException(String message) {
        super(message);
    }

    public BizSystemException(Throwable cause) {
        super(cause);
    }
}
