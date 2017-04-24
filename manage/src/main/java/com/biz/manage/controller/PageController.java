package com.biz.manage.controller;

import com.biz.manage.filter.ManageFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("{path}")
	public ModelAndView forwardToPage(@PathVariable String path) {

		logger.debug("Received {} GET request.");
		return new ModelAndView(path);
	}

	@GetMapping("welcome")
	public ModelAndView forwardToWelcomePath() {

		ManageFilter.cleanActiveMenu();
		return new ModelAndView("welcome");
	}

}
