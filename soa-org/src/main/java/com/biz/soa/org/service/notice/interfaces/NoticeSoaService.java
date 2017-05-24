package com.biz.soa.org.service.notice.interfaces;


import com.biz.gbck.dao.redis.ro.notice.NoticeRo;
import com.biz.vo.notify.NotifyVo;

import java.util.List;


/**
 * 用户消息
 *
 * @author gongshutao
 */

public interface NoticeSoaService {

    /**
     * 后台给对应用户发送消息
     * @param admin
     * @param notifyVo
     */
    void sendNotification(String admin, NotifyVo notifyVo);



    /**
     * 获取未收用户消息
     * @param userId
     * @param lastNoticeId 客户端保存最后一条消息
     * @return
     */
    List<NoticeRo> findUserNoticeAfter(Long userId, Long lastNoticeId);


}
