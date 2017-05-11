package com.biz.gbck.vo.order.resp;

import com.biz.core.util.JsonUtil;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.enums.order.InvoiceType;
import com.biz.gbck.enums.order.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.codelogger.utils.StringUtils;

import java.sql.Timestamp;
import java.util.List;

import static com.biz.gbck.common.Constant.DEFAULT_ORDER_EXPIRE_TIME;
import static com.google.common.collect.Lists.newArrayList;

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
    private Long id;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 订单状态名称
     */
    private String statusDesc;

    /**
     * 订单创建时间
     */
    private Long createTime;

    @JsonIgnore
    private Timestamp createTimestamp;

    /**
     * 订单总价
     */
    private Integer orderAmount;

    /**
     * 支付金额
     */
    private Integer payAmount;

    /**
     * 促销优惠金额
     */
    private Integer freeAmount;

    /**
     * 优惠券抵付金额
     */
    private Integer voucherOffsetAmount;

    /**
     * 付款期限
     */
    private Long payLimitTime;

    /**
     * 支付方式类型{@link PaymentType}
     */
    private Integer paymentType;

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

    /**
     * 判断是否可以现在支付
     */
    private Boolean payable = false;

    /**
     * 是否可以取消
     */
    private Boolean cancelable = false;

    /**
     * 是否显示 联系客服
     */
    private Boolean contactable = true;

    /**
     * 是否显示 再次购买
     */
    private Boolean isBuyAgain = false;

    /**
     * 是否可以申请售后
     */
    private Boolean applyRefundable = false;

    /**
     * 订单备注
     */
    private String description;

    /**
     * 提示文字
     */
    private String hint;

    /**
     * 发票类型{@link InvoiceType}
     */
    private Integer invoiceType;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 订单商品明细
     */
    private List<OrderItemRespVo> items;

    public OrderRespVo() {
    }

    public OrderRespVo(Order order) {
        this();
        this.setId(order.getId());
        this.setOrderCode(order.getOrderCode());
        this.setOrderAmount(order.getOrderAmount());
        this.setPayAmount(order.getPayAmount());
        this.setFreeAmount(order.getFreeAmount());
        this.setVoucherOffsetAmount(order.getVoucherAmount());
        this.setCreateTimestamp(order.getCreateTimestamp());
        this.setCreateTime(order.getCreateTimestamp().getTime());
        this.setStatus(order.getStatus().getValue());
        this.setStatusDesc(order.getStatus().getDesc());
        this.setPaymentType(order.getPaymentType().getValue());
        this.setDescription(order.getDescription());
        this.setInvoiceType(order.getInvoice() != null ? order.getInvoice().getInvoiceType().getValue() : InvoiceType
                .NO.getValue());
        this.setInvoiceTitle(order.getInvoice() != null && StringUtils.isNotBlank(order.getInvoice().getTitle()) ?
                order.getInvoice().getTitle() : null);
        this.setPayLimitTime(this.getCreateTime() + DEFAULT_ORDER_EXPIRE_TIME);

        this.setPayable(order.isPayable());
        this.setCancelable(order.isCancelable(false));
        this.setContactable(order.isContactable());
        this.setBuyAgain(order.isBuyAgain());
        this.setApplyRefundable(order.isReturnable());

    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
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

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
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


    public static void main(String[] args) {
        OrderRespVo vo = new OrderRespVo();
        vo.setItems(newArrayList(new OrderItemRespVo()));
        JsonUtil.printObjectJsonDemo(vo);
        System.out.println(JsonUtil.obj2Json(vo));
    }

}
