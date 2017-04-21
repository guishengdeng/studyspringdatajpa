package com.biz.service.notification;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.info.NoticePo;
import com.biz.gbck.dao.redis.ro.user.UserRo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.service.CommonService;
import com.biz.service.notice.NoticeService;
import com.biz.service.user.UserService;
import com.biz.vo.notify.NotifyVo;
import com.google.common.collect.Lists;
import org.codelogger.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by david-liu on 2016/03/30 16:40.
 */
@Service
public class NotificationService extends CommonService {

    /*@Autowired*/
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    public void sendNotification(String admin, NotifyVo notifyVo) throws CommonException {
        List<Long> userIds = getUserIdsByNotifyVo(notifyVo);
        NoticePo noticePo = new NoticePo();
        noticePo.setTitle(notifyVo.getTitle());
        noticePo.setContent(notifyVo.getNotifyContent());
        noticeService.saveAndSend(noticePo, userIds);
    }

    private List<Long> getUserIdsByNotifyVo(NotifyVo notifyVo) {
        List<Long> userIds = Lists.newArrayList();
        String sourceId;
        if (StringUtils.isNotBlank(notifyVo.getShopTypeId())) {
            sourceId = notifyVo.getShopTypeId();
            for (String shopType : sourceId.split(",")) {
                List<Long> shopTypeUserIds =
                    userService.findUserIdByShopType(Long.valueOf(StringUtils.trimAllWhitespace(shopType)));
                userIds.addAll(shopTypeUserIds);
            }
        } else {
            if(StringUtils.isNotBlank(notifyVo.getMobile())) {
                UserRo userRo = userService.findUserByMobile(notifyVo.getMobile());
                if(userRo != null){
                    userIds.add(userRo.getId());
                }
            } else {
                userIds = userService.findAllUserIdByAuditStatus(AuditStatus.NORMAL);
            }
        }
        return userIds;
    }

}
