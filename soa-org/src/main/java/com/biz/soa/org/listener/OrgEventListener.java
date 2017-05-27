package com.biz.soa.org.listener;

import com.biz.gbck.dao.redis.repository.org.UserRedisDao;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.soa.org.event.AutoLoginEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * SoaOrg Event Listener
 *
 * Created by david-liu on 2017/05/27 12:27.
 */
@Component
public class OrgEventListener {

    @Autowired
    private UserRedisDao userRedisDao;

    @EventListener
    public void handleAutoLoginEvent(AutoLoginEvent autoLoginEvent) {
        UserRo userRo = autoLoginEvent.getUserRo();
        userRo.setLastLoginDeviceToken(autoLoginEvent.getAutoLoginReqVo().getPushToken());
        userRedisDao.save(userRo);
        userRedisDao.saveWithToken(Long.valueOf(userRo.getId()), autoLoginEvent.getAutoLoginReqVo().getGlobalParams().getDeviceId(),
                autoLoginEvent.getAutoLoginReqVo().getPushToken(),
                autoLoginEvent.getAutoLoginReqVo().getGlobalParams().getUserAgent());
    }

}
