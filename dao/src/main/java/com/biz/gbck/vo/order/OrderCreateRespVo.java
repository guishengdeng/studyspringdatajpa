package com.biz.gbck.vo.order;

import com.biz.gbck.vo.user.BaseRequestVo;

/**
 * 创建订单返回Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderCreateRespVo extends BaseRequestVo {

    private Long orderId;

    private String orderCode;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
