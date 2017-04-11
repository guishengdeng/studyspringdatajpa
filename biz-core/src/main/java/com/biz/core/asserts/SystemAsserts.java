package com.biz.core.asserts;

import com.biz.core.exceptions.BusinessException;
import com.biz.core.exceptions.FunctionExceptions;
import com.biz.core.exceptions.SystemException;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统异常断言,检查条件,如不满足条件,则抛出SystemException
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 */
public class SystemAsserts {

    private static Logger logger = LoggerFactory.getLogger(SystemAsserts.class);

    private SystemAsserts() {
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "表达式值必须为true");
    }

    public static void isTrue(boolean expression, String msg, Object... args) {
        if (!expression) {
            String errMsg = String.format(msg, args);
            logger.error(errMsg);
            if(StringUtils.isNotBlank(errMsg)){
                throw new BusinessException(FunctionExceptions.System.SYS_EXCEPTION, errMsg);
            }
            throw new SystemException(errMsg);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "参数不能为null");
    }

    public static void notNull(Object object, String msg, Object... args) {
        if (object == null) {
            String errMsg = String.format(msg, args);
            logger.error(errMsg);
            if(StringUtils.isNotBlank(errMsg)){
                throw new BusinessException(FunctionExceptions.System.SYS_EXCEPTION, errMsg);
            }
            throw new SystemException(errMsg);
        }
    }

    public static void notEmpty(Object[] arr) {
        notEmpty(arr, "参数不能为empty");
    }

    public static void notEmpty(Object[] arr, String msg, Object... args) {
        if (arr == null || arr.length == 0) {
            String errMsg = String.format(msg, args);
            logger.error(errMsg);
            throw new SystemException(errMsg);
        }
    }
    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, "参数不能为empty");
    }

    public static void notEmpty(Collection<?> collection, String msg, Object... args) {
        if (collection == null || collection.isEmpty()) {
            String errMsg = String.format(msg, args);
            logger.error(errMsg);
            throw new SystemException(errMsg);
        }
    }

    public static void notEmpty(Map<?,?> map) {
        notEmpty(map, "参数不能为empty");
    }

    public static void notEmpty(Map<?,?> map, String msg, Object... args) {
        if (map == null || map.isEmpty()) {
            String errMsg = String.format(msg, args);
            logger.error(errMsg);
            throw new SystemException(errMsg);
        }
    }

    public static void hasText(String text, String msg, Object...args) {
        if(!StringUtils.isNotBlank(text)){
            String errMsg = String.format(msg, args);
            logger.error(errMsg);
            throw new SystemException(errMsg);
        }
    }

    public static void hasText(String text) {
        hasText(text,"文本不能为空白");
    }
}
