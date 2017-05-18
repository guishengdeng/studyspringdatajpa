package com.biz.rest.controller;

import com.biz.core.exceptions.BusinessException;
import com.biz.core.exceptions.FunctionExceptions;
import com.biz.core.util.JsonUtil;
import com.biz.gbck.vo.user.BaseRequestVo;
import com.biz.support.web.BuildRequestHandler;
import com.biz.support.web.assist.GlobalParams;
import com.biz.support.web.assist.GlobalParamsAware;
import com.biz.support.web.assist.IPAddressAware;
import com.biz.support.web.handler.JSONResult;
import com.biz.support.web.util.HttpServletHelper;
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
import java.math.BigDecimal;
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

        if (vo instanceof IPAddressAware) {
            IPAddressAware ipvo = (IPAddressAware) vo;
            ipvo.setIp(HttpServletHelper.getClientIP(request));
        }
        if (vo instanceof GlobalParamsAware) {
            // 初始化全局参数
            GlobalParams globalParams = parseGlobalParams(request);
            ((GlobalParamsAware) vo).setGlobalParams(globalParams);
            if (vo instanceof BaseRequestVo) {
                ((BaseRequestVo) vo).setUserId(String.valueOf(globalParams.getUserId()));
            }
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

    /**
     * 解析全局参数
     * <p/>
     * <p>
     * 解析出错时, 返回null, 打印错误日志
     * </p>
     *
     * @param httpRequest
     * @author zhujun
     * @date 2015-2-3
     */
    public GlobalParams parseGlobalParams(HttpServletRequest httpRequest) {
        GlobalParams params = null;
        try {
            GlobalParams p = new GlobalParams();
            p.setApn(httpRequest.getParameter("apn"));
            p.setDeviceId(httpRequest.getParameter("deviceId"));
            String latStr = httpRequest.getParameter("lat");
            if (StringUtils.isNotEmpty(latStr)) {
                try {
                    p.setLat(new BigDecimal(latStr));
                } catch (Exception e) {
                }
            }
            String lonStr = httpRequest.getParameter("lon");
            if (StringUtils.isNotEmpty(lonStr)) {
                try {
                    p.setLon(new BigDecimal(lonStr));
                } catch (Exception e) {
                }
            }
            p.setOs(httpRequest.getParameter("os"));
            p.setOsVersion(httpRequest.getParameter("osVersion"));

            p.setPartner(httpRequest.getParameter("partner"));
            p.setSign(httpRequest.getParameter("sign"));
            p.setSub(httpRequest.getParameter("sub"));
            p.setUserAgent(httpRequest.getParameter("userAgent"));
            String userIdStr = httpRequest.getParameter("userId");
            if (StringUtils.isNumeric(userIdStr)) {
                p.setUserId(Long.valueOf(userIdStr));
            }
            p.setVer(httpRequest.getParameter("ver"));
            p.setMac(httpRequest.getParameter("mac"));
            p.setImsi(httpRequest.getParameter("imsi"));
            p.setImei(httpRequest.getParameter("imei"));
            p.setRouterMac(httpRequest.getParameter("routerMac"));
            p.setStation(httpRequest.getParameter("station"));
            params = p;
        } catch (Exception e) {
            logger.error("解析全局参数出错", e);
        }

        return params;
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
