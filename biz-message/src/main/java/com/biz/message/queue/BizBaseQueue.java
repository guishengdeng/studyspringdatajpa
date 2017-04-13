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
     * OMS推送普通送订单消息队列
     */
    MQ_OMS_ORDER_NORMAL_QUEUE("omsOrderNormalQueue", true, QueueType.queue),

    /**
     * OMS推送立即送送订单消息队列
     */
    MQ_OMS_ORDER_RIGHT_NOW_QUEUE("omsOrderRightNowQueue", true, QueueType.queue),

    /**
     * OMS创建或者修改会员消息队列
     */
    MQ_OMS_MEMBER_CREATE_QUEUE("omsMemberCreateQueue", true, QueueType.queue),

    /**
     * OMS会员重置密码消息队列
     */
    MQ_OMS_MEMBER_RESET_PASSWORD_QUEUE("omsMemberResetPasswordQueue", true, QueueType.queue),

    /**
     * OMS订单状态回写消息队列
     */
    MQ_OMS_ORDER_RESERVE_QUEUE("omsOrderReserveQueue", true, QueueType.queue),

    /**
     * MQ OMS增量库存消息队列
     */
    MQ_OMS_STOCK_ALL_QUEUE("omsStockAllQueue", true, QueueType.queue),

    /**
     * MQ OMS增量库存消息队列
     */
    MQ_OMS_STOCK_CHANGE_QUEUE("omsStockChangeQueue", true, QueueType.queue),

    /**
     * MQ OMS商品消息队列
     */
    MQ_OMS_PRODUCT_QUEUE("omsProductQueue", true, QueueType.queue),

    /**
     * MQ OMS组合商品消息队列
     */
    MQ_OMS_MIX_PRODUCT_QUEUE("omsMixProductQueue", true, QueueType.queue),

    /**
     * MQ OMS门店消息队列
     */
    MQ_OMS_DEPOT_QUEUE("omsDepotQueue", true, QueueType.queue),

    /**
     * MQ OMS门店促销消息队列
     */
    MQ_OMS_DEPOT_PROMOTION_QUEUE("omsDepotPromotionQueue", true, QueueType.queue),

    /**
     * OSS全量数据队列
     */
    MQ_OSS_ALL_DATA_QUEUE("ossAllDataQueue", true, QueueType.queue),

    /**
     * MQ OMS价格消息队列
     */
    MQ_OMS_PRICE_QUEUE("omsPriceQueue", true, QueueType.queue),

    /**
     * MQ OMS会员变更消息队列
     */
    MQ_OMS_MEMBER_CHANGE_QUEUE("omsMemberChangeQueue", true, QueueType.queue),

    /**
     * MQ OMS员工信息队列
     */
    MQ_OMS_EMPLOYEE_QUEUE("omsEmployeeQueue", true, QueueType.queue),

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
     * 区域商品增量库存改变通知搜索建立索引队列
     */
    SEARCH_AREASTOCK_QUEUE("searchAreaStockQueue", true, QueueType.queue),

    /**
     * 门店商品增量库存改变通知搜索建立索引队列
     */
    SEARCH_DEPOTSTOCK_QUEUE("searchDepotStockQueue", true, QueueType.queue),

    /**
     * 快喝促销数据队列名
     */
    KUAIHEPROMOTIONQUEUE_QUEUE("kuaihePromotionQueue", true, QueueType.queue),

    /**
     * 新增或者编辑会员收货地址队列名
     */
    MQ_OMS_SHIPPINGADDRESS_QUEUE("omsShippingAddressQueue", true, QueueType.queue),

    /**
     * 删除会员收货地址队列名
     */
    MQ_OMS_DELETE_SHIPPINGADDRESS_QUEUE("omsDeleteShippingAddressQueue", true, QueueType.queue),

    /**
     * 设置默认收货地址队列名
     */
    MQ_OMS_DEFAULT_SHIPPINGADDRESS_QUEUE("omsDefaultShippingAddressQueue", true, QueueType.queue),

    /**
     * MQ OMS省仓队列
     */
    MQ_OMS_WAREHOUSE_QUEUE("omsWarehouseQueue", true, QueueType.queue),

    /**
     * 未通过校验的中台消息队列
     */
    MQ_INVALID_OMS_MSG_QUEUE("omsInvalidMsgQueue", true, QueueType.queue),

    /**
     * ES商品索引消息队列
     */
    MQ_SEARCH_PRODUCT_QUEUE("elasticSearchProductIdxQueue", true, QueueType.queue),

    /**
     * ES门店索引消息队列
     */
    MQ_SEARCH_DEPOT_QUEUE("elasticSearchDepotIdxQueue", true, QueueType.queue),

    /**
     * ES商家索引消息队列
     */
    MQ_SEARCH_VENDOR_QUEUE("elasticSearchVendorIdxQueue", true, QueueType.queue),

    /**
     * 商品销量消息队列
     */
    MQ_PRODUCT_SALE_STATS_QUEUE("productSaleStatsQueue", true, QueueType.queue);

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
