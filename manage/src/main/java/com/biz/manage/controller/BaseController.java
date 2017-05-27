package com.biz.manage.controller;

import com.biz.manage.exception.InvalidParameterException;
import com.biz.manage.exception.NumberIdParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	public static final String MULTIPLE_MESSAGE_ATTR_NAME = "multiple_messages";

	public static final String SINGLE_MESSAGE_ATTR_NAME = "single_messages";

	@ExceptionHandler({ Exception.class })
	public ModelAndView exception(Exception e) throws Exception {

		ModelAndView result = new ModelAndView("/common/400");
		if( e instanceof MethodArgumentTypeMismatchException){
			return result.addObject(SINGLE_MESSAGE_ATTR_NAME, format("参数%s=%s不正确.\n详情:%s", ((MethodArgumentTypeMismatchException) e).getName(), ((MethodArgumentTypeMismatchException) e).getValue(), e.getMessage()));
		} else if (e instanceof NumberIdParameterException) {
			return result.addObject(SINGLE_MESSAGE_ATTR_NAME, "错误的ID");
		} else if (e instanceof InvalidParameterException) {
			InvalidParameterException invalidParameterException = (InvalidParameterException) e;
			List<ObjectError> objectErrors = invalidParameterException.getErrors().getAllErrors();
			List<String> error_messages = new ArrayList<>();
			for (ObjectError error : objectErrors) {
				if (logger.isDebugEnabled()) {
					logger.debug("objName:{},arg [{}]", error.getObjectName(), error.getArguments());
					for (Object obj : error.getArguments()) {
						logger.debug("obj:{}", obj);
						logger.debug("default message:{}", error.getDefaultMessage());
					}
				}
				String[] codes = null;
				if (error.getArguments() != null && error.getArguments().length > 0) {
					Object arg1 = error.getArguments()[0];
					if (arg1 instanceof DefaultMessageSourceResolvable) {
						DefaultMessageSourceResolvable messageSourceResolvable = (DefaultMessageSourceResolvable) arg1;
						codes = messageSourceResolvable.getCodes();
					}
				}
				logger.debug("error codes:{}", codes);
				String msg = error.getDefaultMessage();
				error_messages.add(msg);
			}
			return result.addObject(MULTIPLE_MESSAGE_ATTR_NAME, error_messages);
		}
		throw e;
	}

	protected void error(BindingResult result) throws InvalidParameterException {

		if (result.hasErrors()) {
			throw new InvalidParameterException(result);
	}
	}

	protected Number validNumberId(String text) throws NumberIdParameterException {

		try {
			return Long.valueOf(text);
		} catch (Throwable t) {
			throw new NumberIdParameterException();
		}
	}

}
