package com.biz.core.codec;

/**
 * AES加密异常
 *
 * @author defei
 * @date 2017年04月06日
 * @reviewer
 */
public class AesEncryptException extends RuntimeException {

    private static final long serialVersionUID = -8831358639761706899L;

    public AesEncryptException() {
    }

    public AesEncryptException(String message) {
        super(message);
    }

    public AesEncryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public AesEncryptException(Throwable cause) {
        super(cause);
    }

    public AesEncryptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
