package com.biz.gbck.dao.mysql.po.order;

import com.biz.gbck.enums.order.ReturnStatus;
import com.biz.support.jpa.converter.ListStringConverter;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;
import java.util.List;
import static com.google.common.collect.Lists.newArrayList;

/**
 * 退货单
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "ord_order_return", indexes = {@Index(columnList = "returnCode")})
public class OrderReturn extends BaseEntity {

    private static final long serialVersionUID = 4548093051968768185L;


    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    //退货单编号
    @Column(length = 50, unique = true, nullable = false)
    private String returnCode;

    /**
     * 退款状态
     */
    @Convert(converter = ReturnStatus.Converter.class)
    private ReturnStatus status = ReturnStatus.PRE_AUDIT;

    /**
     * 退款单明细
     */
    @OneToMany(mappedBy = "orderReturn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderReturnItem> items;

    /**
     * 退货收货人姓名
     */
    @Column(length = 50)
    private String name;

    /**
     * 退货收货人电话
     */
    @Column(length = 15)
    private String mobile;

    /**
     * 退货图片
     */
    @Convert(converter = ListStringConverter.class)
    @Column(length = 800)
    private List<String> images = newArrayList();

    //退货理由
    @Column(length = 100)
    private String reason;

    //退货说明
    @Column
    private String description;

    //退款金额
    private Integer returnAmount;

    /**
     * 退货物流公司
     */
    @Column(length = 50)
    private String expressName;

    /**
     * 退货配送单号
     */
    @Column(length = 50)
    private String expressNo;

    @Embedded
    private OrderReturnAudit returnAudit;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public ReturnStatus getStatus() {
        return status;
    }

    public void setStatus(ReturnStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Integer returnAmount) {
        this.returnAmount = returnAmount;
    }

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

    public OrderReturnAudit getReturnAudit() {
        return returnAudit;
    }

    public void setReturnAudit(OrderReturnAudit returnAudit) {
        this.returnAudit = returnAudit;
    }

    public List<OrderReturnItem> getItems() {
        return items;
    }

    public void setItems(List<OrderReturnItem> items) {
        this.items = items;
    }
}
