package com.biz.manage.controller.notification;

/*import com.depotnearby.common.po.admin.Admin;
import com.depotnearby.exception.CommonException;
import com.depotnearby.security.AuthorityUtil;
import com.depotnearby.service.NotificationService;
import com.depotnearby.vo.notify.NotifyVo;*/
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
 * Created by david-liu on 2016/03/29 22:50.
 */

@Controller
@RequestMapping("/manage/notification") @Secured("ROLE_NOTIFICATION")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

   /* @Autowired
    private NotificationService notificationService;*/

    @RequestMapping("add") @PreAuthorize("hasAuthority('OPT_NOTIFICATION_SAVE')")
    public ModelAndView addNotification() {
        return new ModelAndView("notification/add");
    }

    @RequestMapping(value = "save", method = RequestMethod.POST) @PreAuthorize("hasAuthority('OPT_NOTIFICATION_SAVE')")
    public ModelAndView saveNotification(/*NotifyVo notifyVo*/) {
      /*  String errMsg = null;
        Admin currentAdmin = AuthorityUtil.getLoginUser();
        try {
            notificationService.sendNotification(currentAdmin.getName(), notifyVo);
        } catch (CommonException e) {
            logger.warn("执行发送推送失败", e);
            errMsg = e.getMessage();
        }
        return new ModelAndView("notification/add").addObject("errMsg", errMsg);*/
        return new ModelAndView();
    }

}
