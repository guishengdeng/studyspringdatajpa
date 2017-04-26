package com.biz.service.notification;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.info.NoticePo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.mysql.repository.org.UserRepository;
import com.biz.gbck.dao.redis.ro.user.UserRo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.service.CommonService;
import com.biz.service.notice.NoticeService;
import com.biz.service.org.interfaces.UserService;
import com.biz.vo.notify.NotifyVo;
import com.google.common.collect.Lists;
import org.codelogger.utils.CollectionUtils;
import org.codelogger.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationService extends CommonService {

    /*@Autowired*/
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserRepository userRepository;

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
                    userService.findUserIdByShopType(Long.valueOf(StringUtils.trimAllWhitespace(shopType))); // TODO: 17-4-26 没实现对应方法
                userIds.addAll(shopTypeUserIds);
            }
        } else {
            if(StringUtils.isNotBlank(notifyVo.getMobile())) {
               // UserRo userRo = userService.findUserByMobile(notifyVo.getMobile()); // TODO: 17-4-26 没实现对应方法 原方法是从redis取数据
                UserPo userPo = CollectionUtils.getFirstNotNullValue(userRepository.findUserByMobile(notifyVo.getMobile()));
                if(userPo != null){
                    userIds.add(Long.valueOf(userPo.getId()));
                }
            } else {
                userIds = userService.findAllUserIdByAuditStatus(AuditStatus.NORMAL);// TODO: 17-4-26 没实现对应方法
            }
        }
        return userIds;
    }


}
