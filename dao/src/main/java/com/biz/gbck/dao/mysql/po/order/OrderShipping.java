package com.biz.gbck.dao.mysql.po.order;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

/**
 * 订单配送
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Embeddable
public class OrderShipping {

    /**
     * 配送物流名称
     */
    @Column(length = 50)
    private String expressName;

    /**
     * 配送单号
     */
    @Column(length = 30)
    private String expressNo;

    /**
     * 发货时间
     */
    private Date deliveryDate;

    /**
     * 预计到货时间
     */
    private Date expectDeliveryDate;


    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getExpectDeliveryDate() {
        return expectDeliveryDate;
    }

    public void setExpectDeliveryDate(Date expectDeliveryDate) {
        this.expectDeliveryDate = expectDeliveryDate;
    }
}
