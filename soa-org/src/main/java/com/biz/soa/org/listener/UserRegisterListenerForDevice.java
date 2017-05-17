package com.biz.soa.org.listener;

import com.biz.core.event.AbstractBizEventListener;
import com.biz.core.event.BizEvent;
import com.biz.gbck.dao.redis.repository.org.UserRedisDao;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.vo.org.UserRegisterReqVo;
import com.biz.soa.org.event.UserRegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 用户注册 维护设备信息
 */
public class UserRegisterListenerForDevice extends AbstractBizEventListener<BizEvent> {

    private final static Logger logger =
        LoggerFactory.getLogger(UserRegisterListenerForDevice.class);

//    @Autowired
//    private DeviceService deviceService;


    @Override
    protected void handleEvent(BizEvent event) {
        if (event instanceof UserRegisterEvent) {
            UserRegisterEvent userRegisterEvent = (UserRegisterEvent) event;
            UserRo userRo = userRegisterEvent.getUserRo();
            UserRegisterReqVo reqVo = userRegisterEvent.getUserRegisterReqVo();

            if (userRo != null && reqVo != null) {
//                deviceService.saveUseDevice(new UseDeviceVo(userRo.getId(), reqVo.getGlobalParams(), reqVo.getIp(), true));
                userRedisDao.saveWithToken(Long.valueOf(userRo.getId()), userRegisterEvent.getUserRegisterReqVo().getGlobalParams().getDeviceId(), userRegisterEvent.getUserRegisterReqVo().getPushToken(),
                        userRegisterEvent.getUserRegisterReqVo().getGlobalParams().getUserAgent());
            }

        }
    }


    @Autowired
    private UserRedisDao userRedisDao;

}
