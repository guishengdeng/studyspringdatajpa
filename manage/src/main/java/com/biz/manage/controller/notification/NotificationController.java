package com.biz.manage.controller.notification;


import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.manage.util.AuthorityUtil;
import com.biz.service.notification.NotificationService;
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
 * Created dylan
 */

@Controller
@RequestMapping("/manage/notification") @Secured("ROLE_NOTIFICATION")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("add") @PreAuthorize("hasAuthority('OPT_NOTIFICATION_SAVE')")
    public ModelAndView addNotification() {
        return new ModelAndView("notification/add");
    }

    @RequestMapping(value = "save", method = RequestMethod.POST) @PreAuthorize("hasAuthority('OPT_NOTIFICATION_SAVE')")
    public ModelAndView saveNotification(NotifyVo notifyVo) {
        String errMsg = null;
        Admin currentAdmin = (Admin) AuthorityUtil.getLoginUser();

        try {
            notificationService.sendNotification(currentAdmin.getName(), notifyVo);
        } catch (CommonException e) {
            logger.warn("执行发送推送失败", e);
            errMsg = e.getMessage();
        }
        return new ModelAndView("notification/add").addObject("errMsg", errMsg);
    }



}
