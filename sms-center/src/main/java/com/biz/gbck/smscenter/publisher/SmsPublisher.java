package com.biz.gbck.smscenter.publisher;

import com.biz.gbck.smscenter.common.CommonConstant;
import com.biz.gbck.smscenter.common.Message;
import com.biz.gbck.smscenter.common.SendResult;
import com.biz.gbck.smscenter.interfaces.Notification;
import com.biz.gbck.smscenter.interfaces.SmsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Function: Short Message Publisher that will send short message to specify phone.
 * Created by david-liu on 3/10/16.
 */
public class SmsPublisher implements Notification {

    private static Logger logger = LoggerFactory.getLogger(SmsPublisher.class);

    private List<SmsProvider> smsProviderList;

    private MailPublisher mailPublisher;

    private ThreadPoolTaskExecutor threadPool;

    public void setThreadPool(ThreadPoolTaskExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public void setMailPublisher(MailPublisher mailPublisher) {
        this.mailPublisher = mailPublisher;
    }

    @Override
    public void sendNotice(final Message message) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                SmsPublisher.this.doSend(message);
            }
        });
    }

    private void doSend(Message message) {
        boolean isSuccess = false;
        for (SmsProvider smsProvider : smsProviderList) {
            SendResult sendResult = smsProvider.sendNotice(message);
            if(sendResult.getCode() == CommonConstant.SmsResultCode.API_SUCCESS) {
                isSuccess = true;
                logger.info("发送短信成功");
                break;
            } else {
                logger.warn("尝试切换短信服务商发送短信");
            }
        }

        if(!isSuccess) {
            logger.warn("使用发送短信服务发送短信失败");
            mailPublisher.sendNotice(new Message(MailPublisher.ADMIN_MAIN_ADDRESS,
                    String.format("[%s]使用发送短信服务发送短信失败", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))));
        }
    }

    public void setSmsProviderList(List<SmsProvider> smsProviderList) {
        this.smsProviderList = smsProviderList;
    }
}
