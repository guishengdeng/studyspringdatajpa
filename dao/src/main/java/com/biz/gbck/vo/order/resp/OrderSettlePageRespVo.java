package com.biz.gbck.vo.order.resp;

import com.biz.gbck.enums.order.PaymentType;

import java.io.Serializable;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 订单结算返回Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderSettlePageRespVo implements Serializable {

    private static final long serialVersionUID = -8415628255360674537L;

    /**
     * 总金额
     */
    private Integer orderAmount;

    // 运费
    private Integer freight = 0;

    /**
     * 付款期限
     */
    private Long payLimitTime;

    /**
     * 商品明细
     */
    private List<OrderItemRespVo> items = newArrayList();

    // 促销列表
    private List<OrderPromotionRespVo> promotions = newArrayList();

    // 可用优惠卷数量
    private Integer coupons = 0;

    /**
     * 可用支付方式 {@link PaymentType}
     */
    private List<Integer> paymentTypes = newArrayList();;

    /**
     * 是否有效
     */
    private boolean valid = true;

    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerMobile;

    /**
     * 买家收货地址
     */
    private String buyerAddress;

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public List<OrderItemRespVo> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRespVo> items) {
        this.items = items;
    }

    public Integer getCoupons() {
        return coupons;
    }

    public void setCoupons(Integer coupons) {
        this.coupons = coupons;
    }

    public List<Integer> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<Integer> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Long getPayLimitTime() {
        return payLimitTime;
    }

    public void setPayLimitTime(Long payLimitTime) {
        this.payLimitTime = payLimitTime;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public List<OrderPromotionRespVo> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<OrderPromotionRespVo> promotions) {
        this.promotions = promotions;
    }
}

