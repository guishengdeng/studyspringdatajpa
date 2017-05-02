package com.biz.core.codec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

public class PasswordUtil {

    public static final String AES_KEY = "e0ab52bb342d4a449c39d21518004fa3";

    /**
     * aes解密
     */
    public static String aes2Original(String aesStr) {
        try {
            AesCryptographer aes = new AesCryptographer();
            byte[] b = aes.decrypt(Hex.decodeHex(aesStr.toCharArray()), StringUtils.left(AES_KEY, 16), StringUtils.right(AES_KEY, 16));
            return new String(b);
        } catch (Exception e) {
            return aesStr;
        }
    }

    /**
     * aes加密
     */
    public static String original2Aes(String original) {
        AesCryptographer aes = new AesCryptographer();
        byte[] b = aes.encrypt(original, StringUtils.left(AES_KEY, 16), StringUtils.right(AES_KEY, 16));
        return Hex.encodeHexString(b);
    }
}
