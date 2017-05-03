package com.biz.gbck.dao.mysql.po.order;


import com.biz.gbck.enums.order.InvoiceType;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 发票
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Embeddable
public class OrderInvoice {

    /**
     * 发票类型
     */
    private InvoiceType invoiceType = InvoiceType.NO;

    /**
     * 发票抬头
     */
    @Column(length = 100)
    private String title = "";

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
