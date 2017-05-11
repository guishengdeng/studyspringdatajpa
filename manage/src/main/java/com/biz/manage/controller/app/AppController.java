package com.biz.manage.controller.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.vo.app.AppVo;
import com.biz.manage.controller.BaseController;
import com.biz.service.app.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * app的内容管理
 * Created by lzz on 2017/4/18.
 */
@Controller
@RequestMapping
@Secured("ROLE_SALETAG")
public class AppController extends BaseController {

    @Autowired
    private AppService appService;

    @RequestMapping("/appConfig")
    @PreAuthorize("hasAuthority('OPT_APP_LIST')")
    public ModelAndView app() {
        AppVo appVo = appService.findLastData();
        return new ModelAndView("manage/application/appConfig").addObject("appVo",appVo);
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_APP_ADDORUPDATE')")
    public ModelAndView addOrUpdate(@Valid AppVo appVo,BindingResult result) {

        error(result);
        appService.addOrUpdate(appVo);
        return new ModelAndView("manage/application/appConfig").addObject("appVo", appVo);
    }

}
