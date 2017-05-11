package com.biz.gbck.dao.redis.ro.order;

import com.biz.gbck.enums.order.OrderShowStatus;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

import java.sql.Timestamp;

/**
 * 订单ro
 *
 * @author lei
 * @date 2017年04月24日
 * @reviewer
 * @see
 */
@Ro(key = "ord:order")
public class OrderRo extends BaseRedisObject<Long> {

    private static final long serialVersionUID = -3914774182010478487L;
    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 退货单Id
     */
    private Long orderReturnId;

    /**
     * 展示状态
     */
    private OrderShowStatus status;

    /**
     * 过期时间
     */
    private Timestamp expireTimestamp;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getOrderReturnId() {
        return orderReturnId;
    }

    public void setOrderReturnId(Long orderReturnId) {
        this.orderReturnId = orderReturnId;
    }

    public OrderShowStatus getStatus() {
        return status;
    }

    public void setStatus(OrderShowStatus status) {
        this.status = status;
    }

    public Timestamp getExpireTimestamp() {
        return expireTimestamp;
    }

    public void setExpireTimestamp(Timestamp expireTimestamp) {
        this.expireTimestamp = expireTimestamp;
    }
}
