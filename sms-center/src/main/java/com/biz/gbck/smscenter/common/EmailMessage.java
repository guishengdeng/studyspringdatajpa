package com.biz.gbck.smscenter.common;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;

public class EmailMessage extends Message {

    /**
     * 收件人
     */
    private List<String> to;

    /**
     * 抄送人
     */
    private List<String> cc;

    /**
     * 标题
     */
    private String subject;

    /**
     * 直接发送内容
     */
    private String content;

    /**
     * 是否包含附件(默认不包含)
     */
    private boolean hasAttachment = false;

    /**
     * 附件
     */
    private EmailAttachment emailAttachment;

    public boolean hasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    /**
     * 添加收件人
     */
    public void addToAddr(String toAddr) {
        if (StringUtils.isNotBlank(toAddr)) {
            if (this.to == null) {
                this.to = Lists.newArrayList();
            }
            this.to.add(toAddr);
        }
    }

    /**
     * 添加收件人
     */
    public void addToAddrs(Collection<String> toAddrs) {
        if (this.to == null) {
            this.to = Lists.newArrayList();
        }
        if (CollectionUtils.isNotEmpty(toAddrs)) {
            CollectionUtils.addAll(this.to, toAddrs.iterator());
        }
    }

    /**
     * 添加抄送人
     */
    public void addCcAddr(String ccAddr) {
        if (StringUtils.isNotBlank(ccAddr)) {
            if (this.cc == null) {
                this.cc = Lists.newArrayList();
            }
            this.cc.add(ccAddr);
        }
    }

    public void addCcAddrs(Collection<String> ccAddrs) {
        if (this.cc == null) {
            this.cc = Lists.newArrayList();
        }
        if (CollectionUtils.isNotEmpty(ccAddrs)) {
            CollectionUtils.addAll(this.cc, ccAddrs.iterator());
        }
    }

    public EmailAttachment getEmailAttachment() {
        return emailAttachment;
    }

    public void setEmailAttachment(EmailAttachment emailAttachment) {
        this.emailAttachment = emailAttachment;
    }

    public static void main(String[] args) {
        List<String> emailAddrs = Lists.newArrayList();
        emailAddrs.add("a");
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setSubject("subject");
        emailMessage.setTo(emailAddrs);
        emailMessage.setCc(emailAddrs);
        emailMessage.setContent("email content");
        EmailAttachment emailAttachment = new EmailAttachment();
        emailAttachment.setAttachmentName("filename");
        emailAttachment.setAttachmentFileType("filetype");
        emailAttachment.setAttachmentContent(new byte[]{1,2,3});
        emailMessage.setEmailAttachment(emailAttachment);
        System.out.println(JSON.toJSONString(emailMessage));
    }
}
