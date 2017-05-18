package com.biz.message.queue;


import com.biz.message.QueueDefinition;
import com.biz.message.QueueType;

/**
 * 用以维护框架底层运行的队列定义
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 */
public enum BizBaseQueue implements QueueDefinition {

    /**
     * 发送短信消息队列
     */
    SMS_QUEUE("smsQueue", true, QueueType.queue),

    /**
     * 消息推送消息队列
     */
    PUSH_QUEUE("notification", true, QueueType.queue),


    /**
     * GEO省变更队列
     */
    MQ_GEO_PROVINCE_QUEUE("omsGeoProvinceQueue", true, QueueType.queue),

    /**
     * GEO市变更队列
     */
    MQ_GEO_CITY_QUEUE("omsGeoCityQueue", true, QueueType.queue),
    /**
     * GEO区变更队列
     */
    MQ_GEO_DISTRICT_QUEUE("omsGeoDistrictQueue", true, QueueType.queue),

    /**
     * ES商品索引消息队列
     */
    MQ_SEARCH_PRODUCT_QUEUE("elasticSearchProductIdxQueue", true, QueueType.queue),

    /**
     * 商品销量消息队列
     */
    MQ_PRODUCT_SALE_STATS_QUEUE("productSaleStatsQueue", true, QueueType.queue),


    /**-------------------------------------depotnearby--------------------------------------------*/
    /**
     * 短信校验码队列
     */
    MQ_SMS_CODE("smsqueue__________", true, QueueType.queue),  // TODO: 17-4-28 正式队列没有下划线

    /**
     * 后台通知消息队列
     */
    MQ_CLIENT_PUSH_MSG("clientPushMessageQueue_______", true, QueueType.queue); // TODO: 17-4-28 正式队列没有下划线


    private final String signature;
    private final boolean automaticCreation;
    private final QueueType type;

    BizBaseQueue(String signature, boolean automaticCreation, QueueType type) {
        this.signature = signature;
        this.automaticCreation = automaticCreation;
        this.type = type;
    }

    public boolean isAutomaticCreation() {
        return automaticCreation;
    }

    public String getSignature() {
        return signature;
    }

    public QueueType getType() {
        return type;
    }

}
