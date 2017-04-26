package com.biz.service.notice;


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
import com.biz.gbck.dao.redis.repository.user.UserRedisDao;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.transform.notice.NoticePoToNoticeRo;
import com.biz.service.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 用户消息
 *
 * @author gongshutao
 */
@Service
public class NoticeService extends CommonService {

    private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeRedisDao noticeRedisDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedisDao userRedisDao;



    public NoticePo saveAndSend(NoticePo po, List<Long> userIds) {
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
    public void doSend(NoticePo notice, Long userId) {

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
     * @param user
     * @return
     */
    public String getPlatfrom(UserRo user) {
        String lastFlag = user.getLastUserAgent();
        if (StringUtils.equalsIgnoreCase(lastFlag, "appstore"))
            return NotificationPlatform.IOS;
        else if (StringUtils.containsIgnoreCase(lastFlag, "mi"))
            return NotificationPlatform.MI;
        else
            return NotificationPlatform.ANDROID;
    }

    /**
     * 发送消息
     * @param notification
     * @throws CommonException
     */
    public void sendNotification(Notification notification) throws CommonException {
        // TODO: 17-4-21 将对象添加入mq消息队列
        /*mqService.send(Message.QUEUE.MQ_CLIENT_PUSH_MSG, notification);*/
    }




}
