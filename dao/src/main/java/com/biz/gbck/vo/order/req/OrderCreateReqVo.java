package com.biz.gbck.vo.order.req;

import com.biz.core.util.JsonUtil;
import com.biz.gbck.enums.order.InvoiceType;

import javax.validation.constraints.Size;

/**
 * 订单返回Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderCreateReqVo extends OrderSettlePageReqVo {

    private static final long serialVersionUID = 8586905363689712387L;

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
     * 发票抬头
     */
    private String invoiceTitle;


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

    public static void main(String[] args) {
        OrderCreateReqVo obj = new OrderCreateReqVo();
        JsonUtil.printObjectJsonDemo(obj);
        System.out.println(JsonUtil.obj2Json(obj));
    }

}
