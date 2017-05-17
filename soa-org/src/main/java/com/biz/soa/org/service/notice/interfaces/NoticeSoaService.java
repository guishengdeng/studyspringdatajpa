package com.biz.soa.org.service.notice.interfaces;


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
import com.biz.gbck.transform.notice.NoticePoToNoticeRo;
import com.biz.service.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户消息
 *
 * @author gongshutao
 */

public interface NoticeSoaService {


    NoticePo saveAndSend(NoticePo po, List<Long> userIds);

    void doSend(NoticePo notice, Long userId);

    /**
     * 获取用户最后登录的手机
     */
    String getPlatfrom(UserRo user);

    /**
     * 发送消息
     */
    void sendNotification(Notification notification) throws CommonException;

    /**
     * 获取未收用户消息
     * @param userId
     * @param lastNoticeId 客户端保存最后一条消息
     * @return
     */
    List<NoticeRo> findUserNoticeAfter(Long userId, Long lastNoticeId);


}
