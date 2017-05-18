package com.biz.soa.org.listener;

import com.alibaba.fastjson.JSONObject;
import com.biz.core.event.AbstractBizEventListener;
import com.biz.core.event.BizEvent;
import com.biz.core.util.DateUtil;
import com.biz.gbck.common.com.AlidayuTemplateCode;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.mysql.repository.org.UserRepository;
import com.biz.gbck.dao.redis.repository.org.UserRedisDao;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.transform.org.UserPoToUserRo;
import com.biz.service.sms.SMSService;
import com.biz.soa.org.event.UserLoginEvent;
import com.biz.soa.org.service.interfaces.SmsSoaService;
import com.biz.soa.org.service.interfaces.UserSoaService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.sql.Timestamp;


/**
 * 用户登录记录
 */
public class UserLoginListener extends AbstractBizEventListener<BizEvent> {


    @Override
    @Transactional
    protected void handleEvent(BizEvent event) {
        if (event instanceof UserLoginEvent) {
            UserLoginEvent userLoginEvent = (UserLoginEvent) event;
            UserRo userRo = userLoginEvent.getUserRo();
            Timestamp lastLoginTime = DateUtil.now();
            String ip = userLoginEvent.getUserLoginReqVo().getIp();
            String token = userLoginEvent.getUserLoginReqVo().getPushToken();
            String deviceId = userLoginEvent.getUserLoginReqVo().getGlobalParams().getDeviceId();
            if (StringUtils.isNotBlank(userRo.getLastLoginDeviceToken())
                    && !StringUtils.equals(deviceId, userRo.getLastLoginDeviceToken())
                    && userSoaService.canUserCreateOrder(userRo.getMobile())) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username", userRo.getMobile());
                    smsSoaService.SMSMsg(userRo.getMobile(), AlidayuTemplateCode.CHANGE_MOBILE_LOGIN, jsonObject);
                } catch (CommonException e) {
                    logger.error("发送更换手机登录短信到[{}]失败", userRo.getMobile(), e);
                }
            }
            logger.debug(
                    "Record user[{}] login from IP[{}] with deviceId[{}] and deviceToken[{}] at {}.",
                    userRo.getId(), ip, deviceId, lastLoginTime);

//            deviceService.saveUseDevice(new UseDeviceVo(userRo.getId(),
//                    userLoginEvent.getUserLoginReqVo().getGlobalParams(), ip, false));

            userRepository.updateUserLatestLoginInfo(Long.valueOf(userRo.getId()), token, lastLoginTime, ip);
            UserPo userPo = userRepository.findOne(Long.valueOf(userRo.getId()));
            userRedisDao.save(new UserPoToUserRo().apply(userPo));
            userRedisDao.saveWithToken(Long.valueOf(userRo.getId()), deviceId, token,
                    userLoginEvent.getUserLoginReqVo().getGlobalParams().getUserAgent());
        }
    }

//    @Override
//    @Transactional
//    public void onApplicationEvent(DepotnearbyEvent event) {
//    }

    @Autowired
    private UserSoaService userSoaService;

    @Autowired
    private UserRedisDao userRedisDao;

    @Autowired
    private UserRepository userRepository;

    // todo liubin
//    @Autowired
//    private DeviceService deviceService;

    @Autowired
    private SmsSoaService smsSoaService;

    private final static Logger logger = LoggerFactory.getLogger(UserLoginListener.class);

}
