package com.biz.gbck.vo.order.resp;

import java.util.List;

/**
 * 优惠券请求Vo
 *
 * @author lei
 * @date 2017年05月20日
 * @reviewer
 * @see
 */
public class OrderCouponReqVo implements IOrderPeriodQueryReqVo {

    private static final long serialVersionUID = -8415628255360674537L;

    //用户id
    private Long userId;

    //订单总金额
    private Integer orderAmount;

    //商品明细
    private List<ProductInfoVo> products;

    //支付方式
    private Integer paymentType;

    //所选优惠券类型id集合
    private List<Long> coupons;

    //订单id
    private Long orderId;




    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public Integer getOrderAmount() {
        return orderAmount;
    }

    @Override
    public List<ProductInfoVo> getProducts() {
        return products;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setProducts(List<ProductInfoVo> products) {
        this.products = products;
    }

    @Override
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public List<Long> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Long> coupons) {
        this.coupons = coupons;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}

