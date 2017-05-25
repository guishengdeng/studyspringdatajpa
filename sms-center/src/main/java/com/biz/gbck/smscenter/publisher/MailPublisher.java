package com.biz.gbck.smscenter.publisher;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.smscenter.common.CommonConstant;
import com.biz.gbck.smscenter.common.EmailAttachment;
import com.biz.gbck.smscenter.common.EmailMessage;
import com.biz.gbck.smscenter.common.Message;
import com.biz.gbck.smscenter.interfaces.Notification;
import com.taobao.top.link.embedded.websocket.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.codelogger.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Function: Mail publisher that will publish mail to specify mail address.
 * Created by david-liu on 3/10/16.
 */
public class MailPublisher implements Notification {

    private static Logger logger = LoggerFactory.getLogger(MailPublisher.class);

    static final String ADMIN_MAIN_ADDRESS = "68646@1919.cn";

    private JavaMailSenderImpl mailSender;

    private String mailFrom;

    private ThreadPoolTaskExecutor threadPool;

    public void setThreadPool(ThreadPoolTaskExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendNotice(final Message message) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                MailPublisher.this.doSend(message);
            }
        });
    }

    private void doSend(Message message) {
        if (org.codelogger.utils.StringUtils.isNotBlank(message.getDestination())) {
            this.sendSimpleMail(message);
        } else {
            EmailMessage emailMessage = null;
            try {
                emailMessage = JSON.parseObject(message.getMessageBody(), EmailMessage.class);
            } catch (Exception e) {
                logger.error("转换Json[{}]到EmailMessage对象失败", message.getMessageBody());
            }
            try {
                this.sendMimeMail(emailMessage);
            } catch (Exception e) {
                logger.error("发送mime邮件失败", e);
            }
        }
    }

    private void sendSimpleMail(Message message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setTo(message.getDestination());
        mailMessage.setSubject(CommonConstant.DEFAULT_MAIL_SUBJECT);
        mailMessage.setText(message.getMessageBody());
        try {
            mailSender.send(mailMessage);
            logger.info("已经成功发送邮件到[{}]", message.getDestination());
        } catch(MailException e) {
            logger.warn("发送邮件到[{}]失败,失败原因[{}]", message.getDestination(), e.getMessage());
        }
    }

    private void sendMimeMail(EmailMessage mailMessage) throws MessagingException {
        MimeMessage mimeMailMessage = this.mailSender.createMimeMessage();
        try {
            MimeMessageHelper
                mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true, "utf-8");
            mimeMessageHelper.setTo(mailMessage.getTo().toArray(new String[]{}));
            mimeMessageHelper.setFrom(mailFrom);
            mimeMessageHelper.setSubject(mailMessage.getSubject());
            mimeMessageHelper.setText(mailMessage.getContent());
            mimeMessageHelper.setSentDate(new Date());
            if (CollectionUtils.isNotEmpty(mailMessage.getCc())) {
                mimeMessageHelper.setCc(mailMessage.getCc().toArray(new String[]{}));
            }

            if (mailMessage.hasAttachment()) {
                EmailAttachment emailAttachment = mailMessage.getEmailAttachment();
                String filename;
                if (StringUtils.contains(emailAttachment.getAttachmentName(), String.format(".%s", emailAttachment.getAttachmentFileType()))) {
                    filename = emailAttachment.getAttachmentName();
                } else {
                    filename = String.format("%s.%s", emailAttachment.getAttachmentName(),
                        emailAttachment.getAttachmentFileType());
                }
                File file = new File(filename);
                try {
                    FileUtils.write(emailAttachment.getAttachmentContent(), file);
                } catch (IOException e) {
                    logger.error("生成附件文件出错", e);
                    return;
                }
                mimeMessageHelper.addAttachment(filename, file);
                mailSender.send(mimeMailMessage);
                FileUtils.delete(file);
            } else {
                mailSender.send(mimeMailMessage);
            }
            logger.info("已经成功发送邮件到[{}]", StringUtils.join(mailMessage.getTo(), ","));
        } catch (MessagingException e) {
            logger.warn("发送邮件到[{}]失败,失败原因[{}]", StringUtils.join(mailMessage.getTo(), ","), e.getMessage());
        }
    }

}
