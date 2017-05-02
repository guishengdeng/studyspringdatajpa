package com.biz.manage.controller.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.gbck.vo.app.AppVo;
import com.biz.service.app.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * app的内容管理
 * Created by lzz on 2017/4/18.
 */
@Controller
@RequestMapping
public class AppController {

    @Autowired
    private AppService appService;

    @RequestMapping("/app")
//    @PreAuthorize("hasAuthority('OPT_APP_LIST')")
    public ModelAndView app() {
        return new ModelAndView("manage/application/app");
    }

    @GetMapping("/find")
//    @PreAuthorize("hasAuthority('OPT_APP_FIND')")
    public ModelAndView find(@RequestParam("id") Long id) {
        App app = appService.findById(id);
        return new ModelAndView("manage/application/app").addObject("App", app);
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('OPT_APP_ADDORUPDATE')")
    public ModelAndView addOrUpdate(AppVo appVo) {
        appService.addOrUpdate(appVo);
        return new ModelAndView("manage/application/app").addObject("AppVo", appVo);
    }

}
