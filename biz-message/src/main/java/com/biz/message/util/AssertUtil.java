package com.biz.message.util;

import com.biz.message.exception.MqException;
import org.apache.commons.lang3.StringUtils;

/**
 * FileName: AssertUtil
 * Description:
 * Author: david-liu
 * CreateTime: 2016-08-02 12:29
 */
public class AssertUtil {
    public static void assertNotNull(Object object, String errMsg) throws MqException {
        if (object == null) throw new MqException(errMsg);
    }

    public static void assertNotNull(Object object) throws Exception {
        if (object == null) {
            throw new Exception("变量object不能为空");
        }
    }

    public static void assertStringNotEmpty(String source, String errMsg) throws MqException {
        if (StringUtils.isBlank(source)) throw new MqException(errMsg);
    }

}
