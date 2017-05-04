package com.biz.gbck.enums.order;


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
    //待收货
    DELIVERED,
    //已完成
    FINISHED,
    //已取消
    CANCELED;

    public static OrderShowStatus valueOf(Integer status) {
        if (status == null || status == 0) {
            return ALL;
        } else if (status == OrderStatus.PRE_PAY.getValue()) {
            return PRE_PAY;
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
