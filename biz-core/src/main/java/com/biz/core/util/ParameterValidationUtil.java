package com.biz.core.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Rest接口工具
 *
 * @author lei
 * @date 2016年9月5日
 */
public class ParameterValidationUtil {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final static Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 校验参数
     */
    public static void validate(Object obj, Class<?>... groups) throws RuntimeException {
        Set<ConstraintViolation<Object>> constraintViolations;
        if (groups == null || groups.length == 0) {
            constraintViolations = VALIDATOR.validate(obj);
        } else {
            constraintViolations = VALIDATOR.validate(obj, groups);
        }
        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            StringBuilder errorInfo = new StringBuilder();
            for (ConstraintViolation<Object> cv : constraintViolations) {
                errorInfo.append(cv.getPropertyPath()).append(", ").append(cv.getMessage()).append("; ");
            }
            throw new RuntimeException("业务参数约束出错:" + errorInfo);
        }
    }
}
