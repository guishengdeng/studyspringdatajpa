package com.biz.gbck.vo.order;

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
     * 优惠券
     */
    private List<Long> usedCoupons = newArrayList();

    /**
     * 促销Id
     */
    private Long promotionId;

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
    private List<OrderCreateItemReqVo> items;

    /**
     * 买家备注
     */
    @Size(max = 255, message = "备注超出长度限制")
    private String description;

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

    public List<OrderCreateItemReqVo> getItems() {
        return items;
    }

    public void setItems(List<OrderCreateItemReqVo> items) {
        this.items = items;
    }
}
