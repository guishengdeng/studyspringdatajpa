package com.depotnextdoor.core.codec;

/**
 * AES解密异常
 *
 * @author defei
 * @date 2017年04月06日
 * @reviewer
 */
public class AesDecryptException extends RuntimeException {

    private static final long serialVersionUID = -5917283691756557660L;

    public AesDecryptException() {
    }

    public AesDecryptException(String message) {
        super(message);
    }

    public AesDecryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public AesDecryptException(Throwable cause) {
        super(cause);
    }

    public AesDecryptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
