package com.biz.gbck.vo.order.resp;

import com.biz.gbck.vo.org.UserInfoVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    private Integer orderAmount = 0;

    /**
     * 促销优惠免额
     */
    private Integer freeAmount = 0;

    /**
     * 优惠券优惠金额
     */
    private Integer voucherAmount = 0;

    // 运费
    private Integer freight = 0;

    /**
     * 支付金额
     */
    private Integer payAmount = 0;

    /**
     * 商品明细
     */
    private List<OrderItemRespVo> items = newArrayList();

    // 促销列表
    private List<OrderPromotionRespVo> promotions = newArrayList();

    // 可用优惠卷数量
    private Integer coupons = 0;

    /**
     * 可用支付方式 {@link OrderPaymentTypeRespVo}
     */
    private List<OrderPaymentTypeRespVo> paymentTypes = newArrayList();

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

    //冗余用户基本信息
    @JsonIgnore
    private UserInfoVo userInfoVo;


    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Integer freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Integer getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(Integer voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
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

    public List<OrderPaymentTypeRespVo> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<OrderPaymentTypeRespVo> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
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

    public UserInfoVo getUserInfoVo() {
        return userInfoVo;
    }

    public void setUserInfoVo(UserInfoVo userInfoVo) {
        this.userInfoVo = userInfoVo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

