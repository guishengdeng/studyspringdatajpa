package com.biz.rest.controller;

import com.biz.service.cover.CoverService;
import com.biz.vo.cover.CoverReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newLinkedHashMap;

@Controller
@RequestMapping("homepage")
public class HomepageController extends BaseRestController {

	private static final Logger logger = LoggerFactory.getLogger(HomepageController.class);

	@Autowired
	@Qualifier("coverFeignService")
	private CoverService coverService;

	@PostConstruct
	public void setup(){

		logger.info("Init HomepageController...");
	}

	@RequestMapping
	@ResponseBody
	public Object productList(HttpServletRequest request, HttpServletResponse response) {

		CoverReqVO reqVo = new CoverReqVO();
		return coverService.getHomePage(reqVo);
	}

}
