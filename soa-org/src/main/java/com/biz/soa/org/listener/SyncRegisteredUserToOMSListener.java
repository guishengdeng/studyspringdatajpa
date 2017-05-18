package com.biz.soa.org.listener;

import com.biz.core.event.AbstractBizEventListener;
import com.biz.core.event.BizEvent;
import com.biz.gbck.common.com.mo.Message;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.vo.mq.MQMessage;
import com.biz.gbck.vo.oms.OMSCreateMemberVo;
import com.biz.soa.org.event.UserRegisterEvent;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Register User To OMS Listener
 * Created by zhangcheng on 2016/7/28.
 */
public class SyncRegisteredUserToOMSListener extends AbstractBizEventListener<BizEvent> {

    private final static Logger logger =
            LoggerFactory.getLogger(SyncRegisteredUserToOMSListener.class);


    @Override
    protected void handleEvent(BizEvent event) {
        if (event instanceof UserRegisterEvent) {
            UserRegisterEvent userRegisterEvent = (UserRegisterEvent) event;
            UserRo userRo = userRegisterEvent.getUserRo();

            syncToOMS(userRo);
        }
    }


    private void syncToOMS(UserRo userRo) {
        try {
            if(userRo != null ){
                OMSCreateMemberVo omsMemberCreateVo = new OMSCreateMemberVo();
                omsMemberCreateVo.setName(userRo.getName());
                // todo liubin
                //omsMemberCreateVo.setChannelCode(IOms.CHANNEL_CODE);
                omsMemberCreateVo.setMobile(userRo.getMobile());
                String password = StringUtils.isNotBlank(userRo.getOriginalPassword()) ? DigestUtils.md5Hex(userRo
                        .getOriginalPassword()) : userRo.getPassword();
                omsMemberCreateVo.setPassword(password);
                omsMemberCreateVo.setOriginalPwd(userRo.getOriginalPassword());

                MQMessage msg = new MQMessage(Message.QUEUE.MQ_OMS_MEMBER_CREATE_QUEUE, omsMemberCreateVo, TimeUnit.SECONDS.toMillis(10), 5, null);
                // todo liubin
                //mqService.sendMessage(msg);
            }
        } catch (Exception e) {
            logger.error("Sync member to oms failed. userRo: {}", userRo);
            e.printStackTrace();
        }
    }

}
