package com.biz.soa.cover.controller;

import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.service.cover.CoverService;
import com.biz.soa.base.SoaBaseController;
import com.biz.vo.cover.CoverReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Soa Cover Controller
 *
 * Created by defei on 2017/04/26 12:09.
 */
@RestController
@RequestMapping(value = "soa/cover")
public class SoaCoverController extends SoaBaseController {

    @Autowired
    private CoverService coverService;

    @RequestMapping(value = "homepage", method = RequestMethod.POST)
    public MicroServiceResult<Object> appProductList(@RequestBody CoverReqVo reqVo) {

        return render200(coverService.getHomePage(reqVo));
    }

}
