package com.biz.rest.controller;

import com.biz.support.web.handler.JSONResult;
import org.codelogger.utils.MathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("init")
public class GlobalController extends BaseRestController {

	private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

	@RequestMapping("upgrade")
	public JSONResult upgrade(@RequestParam(value = "ver", required = true, defaultValue = "0.0.0") String ver, HttpServletRequest request) {

		if (MathUtils.randomInt() % 2 == 0) {
			return new JSONResult(true);
		} else {
			return new JSONResult(false);
		}
	}

}
