package com.biz.gbck.vo.order.req;

import com.biz.gbck.enums.order.InvoiceType;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.vo.user.BaseRequestVo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 订单返回Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderCreateReqVo extends BaseRequestVo {

    /**
     * 订单总金额
     */
    @NotNull(message = "订单总金额不能为空")
    private Integer orderAmount;

    /**
     * 免额金额
     */
    private Integer freeAmount = 0;

    /**
     * 优惠券减免金额
     */
    @NotNull(message = "优惠券减免金额不能为空")
    private Integer voucherOffsetAmount = 0;

    /**
     * 买家备注
     */
    @Size(max = 255, message = "备注超出长度限制")
    private String description;

    /**
     * 优惠券
     */
    private List<Long> usedCoupons = newArrayList();

    /**
     * 促销Id
     */
    private Long promotionId;

    /**
     * 需要用户付款金额
     */
    @NotNull(message = "用户付款金额不能为空")
    private Integer payAmount;

    /**
     * 发票类型{@link InvoiceType}
     */
    private Integer invoiceType = InvoiceType.NO.getValue();

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 支付方式{@link PaymentType}
     */
    private Integer paymentType;

    /**
     * 订单明细
     */
    @NotNull(message = "请至少选择一个商品")
    private List<OrderSettlePageItemReqVo> items;


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

    public Integer getVoucherOffsetAmount() {
        return voucherOffsetAmount;
    }

    public void setVoucherOffsetAmount(Integer voucherOffsetAmount) {
        this.voucherOffsetAmount = voucherOffsetAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getUsedCoupons() {
        return usedCoupons;
    }

    public void setUsedCoupons(List<Long> usedCoupons) {
        this.usedCoupons = usedCoupons;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
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

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public List<OrderSettlePageItemReqVo> getItems() {
        return items;
    }

    public void setItems(List<OrderSettlePageItemReqVo> items) {
        this.items = items;
    }
}
