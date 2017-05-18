package com.biz.soa.org.listener;

import com.biz.core.event.AbstractBizEventListener;
import com.biz.core.event.BizEvent;
import com.biz.gbck.dao.redis.repository.org.UserRedisDao;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.soa.org.event.AutoLoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;


/**
 * 用户自动登录记录
 */
public class AutoLoginListener extends AbstractBizEventListener<BizEvent> {


    @Override
    @Transactional
    protected void handleEvent(BizEvent event) {
        if (event instanceof AutoLoginEvent) {
            AutoLoginEvent autoLoginEvent = (AutoLoginEvent) event;
            UserRo userRo = autoLoginEvent.getUserRo();
            userRo.setLastLoginDeviceToken(autoLoginEvent.getAutoLoginReqVo().getPushToken());
            userRedisDao.save(userRo);
            userRedisDao.saveWithToken(Long.valueOf(userRo.getId()), autoLoginEvent.getAutoLoginReqVo().getGlobalParams().getDeviceId(),
                    autoLoginEvent.getAutoLoginReqVo().getPushToken(),
                    autoLoginEvent.getAutoLoginReqVo().getGlobalParams().getUserAgent());
        }
    }

    @Autowired
    private UserRedisDao userRedisDao;

    private final static Logger logger = LoggerFactory.getLogger(UserLoginListener.class);

}
