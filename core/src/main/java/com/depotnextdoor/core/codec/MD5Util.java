package com.depotnextdoor.core.codec;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import org.codelogger.utils.StringUtils;

/**
 * MD5
 * Created @ClassName: MD5Util By @author jun.liu
 *
 * @date 2016年5月31日 上午11:46:56
 */
public class MD5Util {
    private static String ENCODING = "UTF-8";

    /***
     * MD5加码 生成32位大写md5码
     */
    public static String string2MD5HexUpper(String inStr) {
        return string2MD5HexLower(inStr).toUpperCase();
    }

    /***
     * MD5加码 生成32位小写md5码
     */
    public static String string2MD5HexLower(String inStr) {
        if (StringUtils.isBlank(inStr)) return "";
        byte[] byteArray;
        try {
            byteArray = inStr.getBytes(ENCODING);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return byte2MD5HexLower(byteArray);
    }

    public static String byte2MD5HexUpper(byte[] byteArray) {
        return byte2MD5HexLower(byteArray).toUpperCase();
    }

    public static String byte2MD5HexLower(byte[] byteArray) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            return "";
        }

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
