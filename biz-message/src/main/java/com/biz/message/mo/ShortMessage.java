package com.biz.message.mo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ShortMessage implements Serializable {

    /**
     * 接收人手机号
     */
    private String mobile;

    /**
     * 短信模板枚举
     */
    private SmsTemplate template;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 短信内容参数(具体参数见模板内容)
     */
    private Map<String, String> params = new HashMap<>();

    /**
     * 短信消息进入队列的时间
     */
    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void addParam(String key, String value) {
        this.params.put(key, value);
    }

    public SmsTemplate getTemplate() {
        return template;
    }

    public void setTemplate(SmsTemplate template) {
        this.template = template;
    }

    public void put(String key, String value) {
        params.put(key, value);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
