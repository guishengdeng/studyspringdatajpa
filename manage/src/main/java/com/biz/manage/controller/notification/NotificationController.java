package com.biz.manage.controller.notification;


import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.manage.util.AuthorityUtil;
import com.biz.soa.feign.client.global.NoticeFeignClient;
import com.biz.vo.notify.NotifyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created dylan 消息发送
 */

@Controller
@RequestMapping("/manage/notification") @Secured("ROLE_NOTIFICATION")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NoticeFeignClient noticeFeignClient;

    @RequestMapping("add") @PreAuthorize("hasAuthority('OPT_NOTIFICATION_SAVE')")
    public ModelAndView addNotification() {
        return new ModelAndView("notification/add");
    }

    @RequestMapping(value = "save", method = RequestMethod.POST) @PreAuthorize("hasAuthority('OPT_NOTIFICATION_SAVE')")
    public ModelAndView saveNotification(NotifyVo notifyVo) {
        String errMsg = "消息发送成功";
        Admin currentAdmin = (Admin) AuthorityUtil.getLoginUser();
        noticeFeignClient.sendNotification(currentAdmin.getName(), notifyVo);
        return new ModelAndView("notification/add").addObject("errMsg", errMsg);
    }



}
