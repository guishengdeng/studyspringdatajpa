package com.biz.gbck.dao.redis.ro.mq;

import com.biz.gbck.common.ro.annotation.Ro;
import org.codelogger.utils.MD5Utils;
import org.codelogger.utils.StringUtils;

import java.io.Serializable;

import static java.lang.String.format;
import static org.codelogger.utils.ValueUtils.getValue;

/**
 * Created by defei on 12/5/16.
 */
@Ro(hashKeyPrefix = "mqMsg", idSortSetKey = "mqMsg:all")
public class MQMessageRo implements Serializable {

    private static final long serialVersionUID = 6144266964919405289L;

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
     * 最后处理时间
     */
    private Long latestProcessTime;

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

    public MQMessageRo() {
    }

    public MQMessageRo(String queueName, String dataClass, String data, Long latestProcessTime,
                       Long retryPeriod, Integer failedCount, Integer maxFailedCount, String mailTo) {
        if (StringUtils.isBlank(queueName) || StringUtils.isBlank(data) || StringUtils
            .isBlank(dataClass)) {
            throw new IllegalArgumentException(
                format("queueName[%s], data[%s] and dataClass[%s] can not be blank.", queueName,
                    data, dataClass));
        }
        this.queueName = queueName;
        this.dataClass = dataClass;
        this.data = data;
        this.latestProcessTime = latestProcessTime == null ? System.currentTimeMillis() : latestProcessTime;
        this.retryPeriod = getValue(retryPeriod);
        this.failedCount = getValue(failedCount);
        this.maxFailedCount = getValue(maxFailedCount);
        this.mailTo = mailTo;
    }

    public String getId() {
        return MD5Utils.getMD5(queueName + dataClass + data) + "_" + failedCount;
    }

    /**
     * {@linkplain MQMessageRo#queueName}
     */
    public String getQueueName() {
        return queueName;
    }

    /**
     * {@linkplain MQMessageRo#queueName}
     */
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    /**
     * {@linkplain MQMessageRo#dataClass}
     */
    public String getDataClass() {
        return dataClass;
    }

    /**
     * {@linkplain MQMessageRo#dataClass}
     */
    public void setDataClass(String dataClass) {
        this.dataClass = dataClass;
    }

    /**
     * {@linkplain MQMessageRo#data}
     */
    public String getData() {
        return data;
    }

    /**
     * {@linkplain MQMessageRo#data}
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * {@linkplain MQMessageRo#latestProcessTime}
     */
    public Long getLatestProcessTime() {
        return latestProcessTime;
    }

    /**
     * {@linkplain MQMessageRo#latestProcessTime}
     */
    public void setLatestProcessTime(Long latestProcessTime) {
        this.latestProcessTime = latestProcessTime;
    }

    /**
     * {@linkplain MQMessageRo#retryPeriod}
     */
    public Long getRetryPeriod() {
        return retryPeriod;
    }

    /**
     * {@linkplain MQMessageRo#retryPeriod}
     */
    public void setRetryPeriod(Long retryPeriod) {
        this.retryPeriod = retryPeriod;
    }

    /**
     * {@linkplain MQMessageRo#failedCount}
     */
    public Integer getFailedCount() {
        return failedCount;
    }

    /**
     * {@linkplain MQMessageRo#failedCount}
     */
    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    /**
     * {@linkplain MQMessageRo#maxFailedCount}
     */
    public Integer getMaxFailedCount() {
        return maxFailedCount;
    }

    /**
     * {@linkplain MQMessageRo#maxFailedCount}
     */
    public void setMaxFailedCount(Integer maxFailedCount) {
        this.maxFailedCount = maxFailedCount;
    }

    /**
     * {@linkplain MQMessageRo#mailTo}
     */
    public String getMailTo() {
        return mailTo;
    }

    /**
     * {@linkplain MQMessageRo#mailTo}
     */
    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }
}
