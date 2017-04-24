package com.biz.gbck.vo.mq;

import com.biz.core.util.JsonUtil;
import com.biz.gbck.dao.redis.ro.mq.MQMessageRo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by defei on 12/5/16.
 */
public class MQMessage implements Serializable {

    private static final long serialVersionUID = -3500467693515230755L;

    /**
     * 队列名称
     */
    private String queueName;

    /**
     * 消息类型
     */
    private String dataClass;

    /**
     * 消息值
     */
    private String data;

    /**
     * 重试间隔
     */
    private Long retryPeriod;

    /**
     * 失败次数
     */
    private Integer failedCount;

    /**
     * 最大重试次数
     */
    private Integer maxFailedCount;

    /**
     * 超过最大失败次数，发送邮件给谁
     */
    private String mailTo;

    @Deprecated
    public MQMessage() {
    }

    public MQMessage(String queueName, Object data, Long retryPeriod, Integer maxFailedCount, String mailTo) {
        this.queueName = queueName;
        this.dataClass = data == null ? null : data.getClass().getName();
        this.data = JsonUtil.obj2Json(data);
        this.retryPeriod = retryPeriod;
        this.failedCount = 0;
        this.maxFailedCount = maxFailedCount;
        this.mailTo = mailTo;
    }

    public MQMessage(MQMessageRo mqMessageRo){
        this.queueName = mqMessageRo.getQueueName();
        this.dataClass = mqMessageRo.getDataClass();
        this.data = mqMessageRo.getData();
        this.retryPeriod = mqMessageRo.getRetryPeriod();
        this.failedCount = mqMessageRo.getFailedCount();
        this.maxFailedCount = mqMessageRo.getMaxFailedCount();
        this.mailTo = mqMessageRo.getMailTo();
    }

    /**
     * {@linkplain MQMessage#queueName}
     */
    public String getQueueName() {
        return queueName;
    }

    /**
     * {@linkplain MQMessage#dataClass}
     */
    public String getDataClass() {
        return dataClass;
    }

    /**
     * {@linkplain MQMessage#data}
     */
    public String getData() {
        return data;
    }

    @JsonIgnore
    public Object dataToObject(){
        try {
            return data == null ? null : JsonUtil.json2Obj(data, Class.forName(dataClass));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@linkplain MQMessage#retryPeriod}
     */
    public Long getRetryPeriod() {
        return retryPeriod;
    }

    /**
     * {@linkplain MQMessage#failedCount}
     */
    public Integer getFailedCount() {
        return failedCount;
    }

    /**
     * {@linkplain MQMessage#maxFailedCount}
     */
    public Integer getMaxFailedCount() {
        return maxFailedCount;
    }

    /**
     * {@linkplain MQMessage#mailTo}
     */
    public String getMailTo() {
        return mailTo;
    }

    /**
     * {@linkplain MQMessage#queueName}
     */
    @Deprecated
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    /**
     * {@linkplain MQMessage#dataClass}
     */
    @Deprecated
    public void setDataClass(String dataClass) {
        this.dataClass = dataClass;
    }

    /**
     * {@linkplain MQMessage#data}
     */
    @Deprecated
    public void setData(String data) {
        this.data = data;
    }

    /**
     * {@linkplain MQMessage#retryPeriod}
     */
    @Deprecated
    public void setRetryPeriod(Long retryPeriod) {
        this.retryPeriod = retryPeriod;
    }

    /**
     * {@linkplain MQMessage#failedCount}
     */
    @Deprecated
    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    /**
     * {@linkplain MQMessage#maxFailedCount}
     */
    @Deprecated
    public void setMaxFailedCount(Integer maxFailedCount) {
        this.maxFailedCount = maxFailedCount;
    }

    /**
     * {@linkplain MQMessage#mailTo}
     */
    @Deprecated
    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
