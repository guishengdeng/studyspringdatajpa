package com.depotnextdoor.core.asserts;


import com.depotnextdoor.core.exceptions.BusinessException;
import com.depotnextdoor.core.exceptions.ExceptionType;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * 业务断言,当不满足条件时,抛出封装了具体ExceptionType的BusinessException
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 */
public class BusinessAsserts {

    private BusinessAsserts() {
    }

    public static void isFalse(boolean expression, ExceptionType throwing) {
        isTrue(!expression, throwing, "");
    }

    public static void isTrue(boolean expression, ExceptionType throwing) {
        isTrue(expression, throwing, "");
    }

    public static void isTrue(boolean expression, ExceptionType throwing, String msg, Object... args) {
        if (!expression) {
            throw new BusinessException(throwing, String.format(msg, args));
        }
    }

    public static void notNull(Object object, ExceptionType throwing) {
        notNull(object, throwing, "");
    }
    public static void notNull(Object object, ExceptionType throwing, String msg, Object... args) {
        if (object == null) {
            throw new BusinessException(throwing, String.format(msg, args));
        }
    }
    public static void isNull(Object object, ExceptionType throwing) {
        isNull(object, throwing, "");
    }

    public static void isNull(Object object, ExceptionType throwing, String msg, Object... args) {
        if (object != null) {
            throw new BusinessException(throwing, String.format(msg, args));
        }
    }

    public static void notBlank(String string, ExceptionType throwing) {
        notBlank(string, throwing, "");
    }

    public static void notBlank(String string, ExceptionType throwing, String msg, Object... args) {
        if (StringUtils.isBlank(string)) {
            throw new BusinessException(throwing, String.format(msg, args));
        }
    }

    public static void notEqual(Object object1, Object object2, ExceptionType throwing) {
        notEqual(object1, object2, throwing, "");
    }

    public static void notEqual(Object object1, Object object2, ExceptionType throwing, String msg, Object... args) {
        if (Objects.equal(object1, object2)) {
            throw new BusinessException(throwing, String.format(msg, args));
        }
    }

    public static void isEqual(Object object1, Object object2, ExceptionType throwing) {
        isEqual(object1, object2, throwing, "");
    }

    public static void isEqual(Object object1, Object object2, ExceptionType throwing, String msg, Object... args) {
        if (!Objects.equal(object1, object2)) {
            throw new BusinessException(throwing, String.format(msg, args));
        }
    }

}
