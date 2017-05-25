package com.biz.soa.org.service.notice;


import com.biz.core.notification.Notification;
import com.biz.core.notification.NotificationSendType;
import com.biz.core.notification.NotifyType;
import com.biz.core.notification.PushMessage;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.notification.NotificationPlatform;
import com.biz.gbck.dao.mysql.po.info.NoticePo;
import com.biz.gbck.dao.mysql.repository.notice.NoticeRepository;
import com.biz.gbck.dao.mysql.repository.org.UserRepository;
import com.biz.gbck.dao.redis.repository.notice.NoticeRedisDao;
import com.biz.gbck.dao.redis.repository.org.UserRedisDao;
import com.biz.gbck.dao.redis.ro.notice.NoticeRo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.transform.notice.NoticePoToNoticeRo;
import com.biz.message.MessageService;
import com.biz.message.SimpleBizMessage;
import com.biz.message.queue.BizBaseQueue;
import com.biz.service.CommonService;
import com.biz.soa.org.service.interfaces.UserSoaService;
import com.biz.soa.org.service.notice.interfaces.NoticeSoaService;
import com.biz.vo.notify.NotifyVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.codelogger.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户消息
 *
 * @author dylan 17.5.22
 */
@Service
public class NoticeSoaSoaServiceImpl extends CommonService implements NoticeSoaService {

    private static final Logger logger = LoggerFactory.getLogger(NoticeSoaSoaServiceImpl.class);

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeRedisDao noticeRedisDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedisDao userRedisDao;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserSoaService userSoaService;



    public void sendNotification(String admin, NotifyVo notifyVo){
        List<Long> userIds = getUserIdsByNotifyVo(notifyVo);
        NoticePo noticePo = new NoticePo();
        noticePo.setTitle(notifyVo.getTitle());
        noticePo.setContent(notifyVo.getNotifyContent());
        this.saveAndSend(noticePo, userIds);
    }

    /**
     * 判断后台消息发送对象
     * @param notifyVo
     * @return
     */
    private List<Long> getUserIdsByNotifyVo(NotifyVo notifyVo) {
        List<Long> userIds = Lists.newArrayList();
        String sourceId;
        if (org.codelogger.utils.StringUtils.isNotBlank(notifyVo.getShopTypeId())) {
            sourceId = notifyVo.getShopTypeId();
            for (String shopType : sourceId.split(",")) {
                List<Long> shopTypeUserIds =
                    userSoaService.findUserIdByShopType(Long.valueOf(StringUtils.trimAllWhitespace(shopType)));
                userIds.addAll(shopTypeUserIds);
            }
        } else {
            if(org.codelogger.utils.StringUtils.isNotBlank(notifyVo.getMobile())) {
               UserRo userRo = userSoaService.findUserByMobile(notifyVo.getMobile());
                if(userRo != null){
                    userIds.add(Long.valueOf(userRo.getId()));
                }
            } else {
                userIds = userSoaService.findAllUserIdByAuditStatus(AuditStatus.NORMAL);
            }
        }
        return userIds;
    }






    private NoticePo saveAndSend(NoticePo po, List<Long> userIds) {
        if (po == null) {
            throw new IllegalArgumentException("noticepo is null");
        }
        if (po.getId() == null)
            po.setId(idService.nextId());

        noticeRepository.save(po);
        noticeRedisDao.save((new NoticePoToNoticeRo()).apply(po));
        List<Long> sendToUsers = null;

        if (CollectionUtils.isNotEmpty(userIds)) {
            sendToUsers = userIds;
        } else if (po.getShopType() != null) {
            sendToUsers = userRepository.findUserIdsByShopType(po.getShopType().getId());
        } else if (po.getSaleArea() != null) {
            sendToUsers = userRepository.findUserIdsBySaleAreaId(po.getSaleArea().getId());
        }
        for (Long userId : sendToUsers) {
            doSend(po, userId);
        }
        return po;
    }


    /**
     * 获取未收用户消息
     * @param userId
     * @param lastNoticeId 客户端保存最后一条消息
     * @return
     */
    @Override
    public List<NoticeRo> findUserNoticeAfter(Long userId, Long lastNoticeId) {
        List<NoticeRo> result = noticeRedisDao.findAfter(userId, lastNoticeId);
        noticeRedisDao.deleteAfter(userId, lastNoticeId);
        return result;
    }

    /**
     * 封装要发送的消息
     * @param notice
     * @param userId
     */
    private void doSend(NoticePo notice, Long userId) {

        UserRo user = userRedisDao.get(userId);
        if (user != null) {
            if (notice.getId() == null)
                notice.setId(idService.nextId());

            noticeRepository.save(notice);
            noticeRedisDao.addToUser(notice.getId(), userId);
            int remainMsgCount = noticeRedisDao.getRemainMSgCount(user.getId()).intValue();
            if (remainMsgCount == 0) {
                remainMsgCount++;
            }

            Notification pushMsg = new Notification();
            pushMsg.setNotifyType(NotifyType.P2P);

            pushMsg.setTarget(user.getLastToken());
            pushMsg.setPlatform(getPlatfrom(user));
            pushMsg.setSendType(NotificationSendType.ALERT);
            pushMsg.setPushMessage(
                    new PushMessage(remainMsgCount, notice.getContent(), notice.getUrl()));
            try {
                sendNotification(pushMsg); //推送消息
            } catch (CommonException e) {
                logger.warn("发送推送消息到用户[{}]失败", userId);
            }
        }
    }

    /**
     * 获取用户最后登录的手机
     */
    private String getPlatfrom(UserRo user) {
        String lastFlag = user.getLastUserAgent();
        if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(lastFlag, "appstore"))
            return NotificationPlatform.IOS;
        else if (StringUtils.containsIgnoreCase(lastFlag, "mi"))
            return NotificationPlatform.MI;
        else
            return NotificationPlatform.ANDROID;
    }


    /**
     * 发送消息
     */
    private void sendNotification(Notification notification) throws CommonException {
        messageService.sendMessage(BizBaseQueue.MQ_CLIENT_PUSH_MSG, SimpleBizMessage.newMessage(notification));
    }



}
