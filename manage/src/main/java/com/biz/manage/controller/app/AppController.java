package com.biz.manage.controller.app;

import com.biz.core.util.StringUtil;
import com.biz.gbck.dao.mysql.po.app.App;
import com.biz.service.IdService;
import com.biz.service.app.AppService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.java2d.pipe.AlphaPaintPipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;

/**
 * app的内容管理
 * Created by lzz on 2017/4/18.
 */
@Controller
@RequestMapping("manage")
public class AppController {

    @Autowired
    private AppService  appService;
    @Autowired
    private IdService idService;

    @RequestMapping("/app")
    @PreAuthorize("hasAuthority('OPT_APP_APP')")
    public ModelAndView app(){
        ModelAndView view =new ModelAndView("manage/application/app");
        return  view;
    }

    @GetMapping("/find")
    @PreAuthorize("hasAuthority('OPT_APP_FIND')")
    public ModelAndView findApp(@RequestParam("id") String id){

            App apps = appService.findById(id);
            ModelAndView modelAndView = new ModelAndView("manage/application/app").addObject("apps", apps);

            return modelAndView;

    }

    @RequestMapping(value= "/addOrUpdate", method= RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('OPT_APP_addOrUpdate')")
    public ModelAndView insert(App apps){
        appService.insert(apps);
        ModelAndView ModelAndView= new ModelAndView("manage/application/app").addObject("apps",apps);
       return ModelAndView;
    }


}
