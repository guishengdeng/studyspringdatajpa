package com.biz.soa.org.listener;


import com.biz.core.event.AbstractBizEventListener;
import com.biz.core.event.BizEvent;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.soa.org.event.UserResetPasswordEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Reset user's password
 * Created by zhangcheng on 2016/7/28.
 */
public class UserResetPasswordListener extends AbstractBizEventListener<BizEvent> {

    private final static Logger logger =
            LoggerFactory.getLogger(UserResetPasswordListener.class);

    @Override
    protected void handleEvent(BizEvent event) {
        if (event instanceof UserResetPasswordEvent){
            UserResetPasswordEvent UserChangePasswordEvent = (UserResetPasswordEvent) event;
            UserRo userRo = UserChangePasswordEvent.getUserRo();
            syncToOMS(userRo);
        }
    }

    private void syncToOMS(UserRo userRo) {
        try {
            if (userRo != null){
                // todo liubin
//                OMSResetMemberPasswordVo resetPwdVo = new OMSResetMemberPasswordVo();
//                resetPwdVo.setMobile(userRo.getMobile());
//                resetPwdVo.setMemberId(userRo.getShopId()); //会员编号＝>shopId
//                resetPwdVo.setPassword(userRo.getPassword());
//                resetPwdVo.setOriginalPwd(userRo.getOriginalPassword());
//                resetPwdVo.setChannelCode(IOms.CHANNEL_CODE);
//
//                MQMessage msg = new MQMessage(Message.QUEUE.MQ_OMS_MEMBER_RESET_PASSWORD_QUEUE, resetPwdVo, TimeUnit.SECONDS.toMillis(10), 5, null);
//                mqService.sendMessage(msg);
            }
        } catch (Exception e) {
            logger.error("Sync reset password failed. userRo: {}", userRo);
            e.printStackTrace();
        }
    }
}
