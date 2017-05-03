package com.biz.gbck.vo.order;

import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.enums.order.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.List;

/**
 * 订单返回Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderRespVo implements Comparable<OrderRespVo> {

    /**
     * 订单id
     */
    public Long id;

    /**
     * 订单编号
     */
    public String orderCode;

    /**
     * 订单状态
     */
    @JsonIgnore
    public OrderStatus status;

    /**
     * 订单状态名称
     */
    public String statusName;

    /**
     * 订单创建时间
     */
    public Long createTime;

    @JsonIgnore
    private Timestamp createTimestamp;

    /**
     * 订单总价
     */
    public Integer orderAmount;

    /**
     * 支付金额
     */
    public Integer payAmount;

    /**
     * 促销优惠金额
     */
    public Integer freeAmount;

    /**
     * 优惠券抵付金额
     */
    public Integer voucherOffsetAmount;

    /**
     * 付款期限
     */
    public Long payLimitTime;

    /**
     * 支付方式类型
     */
    public PaymentType paymentType;

    /**
     * 买家id
     */
    public Long buyerId;

    /**
     * 买家姓名
     */
    public String buyerName;

    /**
     * 买家手机号
     */
    public String buyerMobile;

    /**
     * 买家收货地址
     */
    public String buyerAddress;

    /**
     * 判断是否可以现在支付
     */
    public Boolean payable;

    /**
     * 是否可以取消
     */
    public Boolean cancelable;

    /**
     * 是否显示 联系客服
     */
    public Boolean contactable;

    /**
     * 是否显示 再次购买
     */
    public Boolean isBuyAgain;

    /**
     * 是否可以申请售后
     */
    public Boolean applyRefundable;

    /**
     * 订单备注
     */
    public String description;

    /**
     * 提示文字
     */
    public String hint;

    /**
     * 发票信息
     */
    public String ticket;

    /**
     * 订单商品明细
     */
    public List<OrderItemRespVo> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Integer freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Integer getVoucherOffsetAmount() {
        return voucherOffsetAmount;
    }

    public void setVoucherOffsetAmount(Integer voucherOffsetAmount) {
        this.voucherOffsetAmount = voucherOffsetAmount;
    }

    public Long getPayLimitTime() {
        return payLimitTime;
    }

    public void setPayLimitTime(Long payLimitTime) {
        this.payLimitTime = payLimitTime;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
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

    public Boolean getPayable() {
        return payable;
    }

    public void setPayable(Boolean payable) {
        this.payable = payable;
    }

    public Boolean getCancelable() {
        return cancelable;
    }

    public void setCancelable(Boolean cancelable) {
        this.cancelable = cancelable;
    }

    public Boolean getContactable() {
        return contactable;
    }

    public void setContactable(Boolean contactable) {
        this.contactable = contactable;
    }

    public Boolean getBuyAgain() {
        return isBuyAgain;
    }

    public void setBuyAgain(Boolean buyAgain) {
        isBuyAgain = buyAgain;
    }

    public Boolean getApplyRefundable() {
        return applyRefundable;
    }

    public void setApplyRefundable(Boolean applyRefundable) {
        this.applyRefundable = applyRefundable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public List<OrderItemRespVo> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRespVo> items) {
        this.items = items;
    }

    @Override
    public int compareTo(OrderRespVo o) {
        return o.createTimestamp.compareTo(this.createTimestamp);
    }


}
