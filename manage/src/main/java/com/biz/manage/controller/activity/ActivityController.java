package com.biz.manage.controller.activity;

import com.biz.gbck.activity.frontend.ActivityVo;
import com.biz.service.activity.interfaces.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by xys on 2017/4/18.
 */
@Controller
@RequestMapping("manage/activity")
public class ActivityController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/list")
    public ModelAndView toList() {
        List<ActivityVo> activitys = activityService.findAllActivitys();
        ModelAndView view = new ModelAndView("manage/activity/list","activitys", activitys);
        return view;
    }

    /**
     * 跳转到新建广告页面
     */
    @RequestMapping("/add")
    public ModelAndView toAdd() {
        ModelAndView view = new ModelAndView("manage/activity/add");
        return view;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam("id") String id) {
        ModelAndView view = new ModelAndView("manage/activity/add", "activity", activityService.findById(id));
        view.addObject("cmd", "edit");
        return view;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam("id") String id) {
        activityService.delete(id);
        return new ModelAndView("redirect:/manage/activity/list.do");
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveOrUpdate(ActivityVo req) {
        activityService.saveOrUpdateActivity(req);
        return new ModelAndView("redirect:/manage/activity/list");
    }
}
