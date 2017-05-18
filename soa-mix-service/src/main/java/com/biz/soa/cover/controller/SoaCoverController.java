package com.biz.soa.cover.controller;

import com.biz.service.cover.CoverService;
import com.biz.soa.base.SoaBaseController;
import com.biz.vo.cover.CoverReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Soa Cover Controller
 * <p/>
 * Created by defei on 2017/04/26 12:09.
 */
@RestController
@RequestMapping(value = "soa/cover")
public class SoaCoverController extends SoaBaseController {

	@Autowired
	private CoverService coverService;

	@RequestMapping(value = "homepage", method = RequestMethod.POST)
	public Object appProductList(@RequestBody CoverReqVO reqVo) {

		return coverService.getHomePage(reqVo);
	}

}
