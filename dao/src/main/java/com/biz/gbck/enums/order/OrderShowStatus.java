package com.biz.gbck.enums.order;


import org.codelogger.utils.ValueUtils;

/**
 * 订单展示状态
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum OrderShowStatus {
    //全部
    ALL,
    //待支付
    PRE_PAY,
    //待发货
    ORDERED,
    //待收货
    DELIVERED,
    //已完成
    FINISHED,
    //已取消
    CANCELED;

    public static OrderShowStatus valueOf(Integer status) {
        if (ValueUtils.getValue(status) == 0) {
            return ALL;
        } else if (status == OrderStatus.PRE_PAY.getValue()) {
            return PRE_PAY;
        } else if(status == OrderStatus.ORDERED.getValue()) {
            return ORDERED;
        } else if ( status == OrderStatus.DELIVERED.getValue()) {
            return DELIVERED;
        } else if (status == OrderStatus.FINISHED.getValue()) {
            return FINISHED;
        } else if (status == OrderStatus.CANCELED.getValue()) {
            return CANCELED;
        }
        return null;
    }

}
