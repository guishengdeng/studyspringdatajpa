package com.biz.core.exceptions;


import com.biz.core.asserts.SystemAsserts;

/**
 * 所有与程序编码有关的异常统一抛出本类及其子类, 使用系统断言{@link SystemAsserts}来进行快速检查
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 * @see SystemAsserts
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -6415147203498700978L;

    public SystemException() {
        super();
    }

    public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

}
