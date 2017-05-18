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
    protected Logger logger = LoggerFactory.getLogger(this.getClass());




}
