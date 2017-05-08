package com.biz.gbck.vo.order.resp;

import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.vo.user.BaseRequestVo;

import java.util.List;

/**
 * 订单结算请求Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderSettlePageRespVo extends BaseRequestVo {

    private static final long serialVersionUID = -8415628255360674537L;

    /**
     * 总金额
     */
    private Integer orderAmount;

    // 运费
    private Integer freight = 0;

    /**
     * 商品明细
     */
    private List<OrderItemRespVo> items;

    // 促销列表
    private List<OrderPromotionRespVo> promotions;

    // 可用优惠卷数量
    private Integer coupons = 0;

    /**
     * 可用支付方式 {@link PaymentType}
     */
    private List<Integer> paymentTypes;

    /**
     * 是否有效
     */
    private boolean valid = true;

    /**
     * 卖家名称
     */
    private String sellerName;

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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
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

