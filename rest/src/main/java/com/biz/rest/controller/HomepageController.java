package com.biz.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("homepage")
public class HomepageController extends BaseRestController {

	private static final Logger logger = LoggerFactory.getLogger(HomepageController.class);

	@PostConstruct
	public void setup(){

		logger.info("Init HomepageController...");
	}

	@RequestMapping
	public String productList(HttpServletRequest request, HttpServletResponse response) {

		return "[{\"type\": \"SlideBanner\", \"items\": [{\"imgUrl\": \"https://1919-new-bbc-pro.oss-cn-beijing.aliyuncs.com/25f18acf-0437-436f-95b4-f2a0f4abe264\", \"link\": \"https://www.baidu.com\"} ], \"interval\": 5 }, {\"type\": \"ImageNavigation\", \"items\": [{\"imgUrl\": \"https://1919-new-bbc-pro.oss-cn-beijing.aliyuncs.com/25f18acf-0437-436f-95b4-f2a0f4abe264\", \"link\": \"https://www.baidu.com\"} ] }, {\"type\": \"ImageShowcase\", \"left\": {\"imgUrl\": \"https://1919-new-bbc-pro.oss-cn-beijing.aliyuncs.com/25f18acf-0437-436f-95b4-f2a0f4abe264\", \"link\": \"https://www.baidu.com\"}, \"rightTop\": {\"imgUrl\": \"https://1919-new-bbc-pro.oss-cn-beijing.aliyuncs.com/25f18acf-0437-436f-95b4-f2a0f4abe264\", \"link\": \"https://www.baidu.com\"}, \"rightBottom\": {\"imgUrl\": \"https://1919-new-bbc-pro.oss-cn-beijing.aliyuncs.com/25f18acf-0437-436f-95b4-f2a0f4abe264\", \"link\": \"https://www.baidu.com\"} }, {\"type\": \"ProductList\", \"items\": [{\"imgUrl\": \"https://1919-new-bbc-pro.oss-cn-beijing.aliyuncs.com/25f18acf-0437-436f-95b4-f2a0f4abe264\", \"text\" : \"飞天茅台\", \"link\" : \"https://www.baidu.com\"} ] } ]";
	}

}
