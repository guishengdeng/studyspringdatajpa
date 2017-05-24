package com.biz.gbck.vo.order.resp;

import java.util.List;

/**
 * 促销请求Vo
 *
 * @author lei
 * @date 2017年05月20日
 * @reviewer
 * @see
 */
public class OrderPromotionReqVo implements IOrderPeriodQueryReqVo {

    private static final long serialVersionUID = -8415628255360674537L;

    //用户id
    private Long userId;

    //订单总金额
    private Integer orderAmount;

    //商品明细
    private List<? extends IProduct> products;

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public Integer getOrderAmount() {
        return orderAmount;
    }

    @Override
    public List<? extends IProduct> getProducts() {
        return products;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setProducts(List<? extends IProduct> products) {
        this.products = products;
    }

    @Override
    public Integer getPaymentType() {
        return null;
    }

    @Override
    public List<Long> getCoupons() {
        return null;
    }

    @Override
    public Long getOrderId() {
        return null;
    }
}

