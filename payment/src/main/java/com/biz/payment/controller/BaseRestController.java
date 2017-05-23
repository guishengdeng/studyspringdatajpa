package com.biz.rest.controller;

import com.biz.core.exceptions.BusinessException;
import com.biz.core.exceptions.FunctionExceptions;
import com.biz.core.util.JsonUtil;
import com.biz.support.web.BuildRequestHandler;
import com.biz.support.web.handler.JSONResult;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * 控制器基类,要求所有面向手机端的api都要继承此类
 *
 * @author yanweijin
 * @usage
 * @reviewer
 * @since 2016年8月3日
 */
@RestController
public abstract class BaseRestController {
    protected static final String DATA_PARAM_NAME = "data";
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired(required = false)
    private List<BuildRequestHandler> handlers = Lists.newArrayList();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * @param request http请求
     * @param clazz 请求vo的class
     * @param validationGroups 用于jsr303验证的group class
     */
    protected <V> V parseBizData(HttpServletRequest request, Class<V> clazz, Class<?>... validationGroups) {

        String bizData = request.getParameter(DATA_PARAM_NAME);
        logger.debug("请求参数:\n{}", bizData);
        V vo;
        try {
            vo = StringUtils.isNotBlank(bizData) ? JsonUtil.json2Obj(bizData, clazz) : clazz.newInstance();
        } catch (Exception e) {
            logger.error("解析接口业务参数出错", e);
            throw new BusinessException(FunctionExceptions.System.PARAMETER_ERROR, e);
        }

        Class<?>[] groups = null;
        if (validationGroups != null && validationGroups.length > 0) {
            groups = validationGroups;
        }
        try {
            validate(vo, groups);
        } catch (ValidationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof BusinessException) {
                throw (BusinessException) cause;
            }
            throw e;
        }

        handlers.forEach(handler -> handler.handle(request, vo));
        return vo;
    }

    @ExceptionHandler(Exception.class)
    protected JSONResult exceptionHandler(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        if (ex instanceof BusinessException) {
            logger.info("business-exception:{}", ex);
            return new JSONResult((BusinessException) ex);
        } else {
            logger.error("system-exception:{}", ex);
            return new JSONResult(new BusinessException(FunctionExceptions.System.SYS_EXCEPTION, ex.getMessage()));
        }
    }


    protected void validate(Object obj, Class<?>... groups) throws ValidationException {
        Set<ConstraintViolation<Object>> constraintViolations;
        if (groups == null || groups.length == 0) {
            constraintViolations = validator.validate(obj);
        } else {
            constraintViolations = validator.validate(obj, groups);
        }
        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            StringBuilder errorInfo = new StringBuilder();
            for (ConstraintViolation<Object> cv : constraintViolations) {
                errorInfo.append(cv.getMessage()).append("\r\n");
            }
            throw new BusinessException(FunctionExceptions.System.PARAMETER_ERROR, errorInfo.toString());
        }
    }

}
