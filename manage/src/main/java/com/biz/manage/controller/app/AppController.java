package com.biz.manage.controller.app;

import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.service.IdService;
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
@RequestMapping()
public class AppController {

    @Autowired
    private AppService  appService;
    @Autowired
    private IdService idService;

    @RequestMapping("/app")
    @PreAuthorize("hasAuthority('OPT_APP_LIST')")
    public ModelAndView app(){
        return   new ModelAndView("manage/application/app");
    }

    @GetMapping("/find")
    @PreAuthorize("hasAuthority('OPT_APP_FIND')")
    public ModelAndView findApp(@RequestParam("id") String id){
        App app = appService.findById(id);
        ModelAndView modelAndView = new ModelAndView("manage/application/app").addObject("app", app);
        return modelAndView;
    }

    @RequestMapping(value= "/addOrUpdate", method= RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_APP_ADDORUPDATE')")
    public ModelAndView addOrUpdate(App app){
        appService.addOrUpdate(app);
        ModelAndView ModelAndView= new ModelAndView("application/app").addObject("app", app);
        return ModelAndView;
    }

}
