package com.biz.gbck.vo.order.req;

import com.biz.core.util.JsonUtil;
import com.biz.gbck.enums.order.InvoiceType;
import com.biz.gbck.enums.order.PaymentType;

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
public class OrderCreateReqVo extends OrderSettlePageReqVo {

    /**
     * 买家备注
     */
    @Size(max = 255, message = "备注超出长度限制")
    private String description;

    /**
     * 发票类型{@link InvoiceType}
     */
    private Integer invoiceType = InvoiceType.NO.getValue();

    /**
     * 促销Id
     */
    private Long promotionId;

    /**
     * 优惠券
     */
    private List<Long> usedCoupons = newArrayList();


    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 支付方式{@link PaymentType}
     */
    private Integer paymentType;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    @Override
    public List<Long> getUsedCoupons() {
        return usedCoupons;
    }

    @Override
    public void setUsedCoupons(List<Long> usedCoupons) {
        this.usedCoupons = usedCoupons;
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

    public static void main(String[] args) {
        OrderCreateReqVo obj = new OrderCreateReqVo();
        JsonUtil.printObjectJsonDemo(obj);
        System.out.println(JsonUtil.obj2Json(obj));
    }

}
