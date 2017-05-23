package com.biz.gbck.dao.mysql.po.purchase;

import com.biz.gbck.enums.purchase.InOutType;
import com.biz.gbck.enums.purchase.OperationStatus;
import com.biz.gbck.enums.purchase.UserType;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 出库单
 *
 * @author lei
 * @date 2017年04月26日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pur_delivery_order")
public class DeliveryOrder extends BaseEntity {

    private static final long serialVersionUID = -7749433829358520328L;

    /**
     * 出库单号
     */
    @Column(length = 50)
    private String sn;

    /**
     * 对应订单编码
     */
    @Column(length = 50)
    private String orderCode;

    //入库明细
    @OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryOrderItem> items;

    /**
     * 出入库类型
     */
    @Convert(converter = InOutType.Converter.class)
    private InOutType inOutType;

    /**
     * 发货方Id
     */
    private Long shipperId;

    /**
     * 发货方类型
     */
    @Convert(converter = UserType.Converter.class)
    private UserType shipperType;


    /**
     * 收货方Id
     */
    private Long recipientId;

    /**
     * 收货方类型
     */
    @Convert(converter = UserType.Converter.class)
    private UserType recipientType;

    /**
     * 处理状态
     */
    @Convert(converter = OperationStatus.Converter.class)
    private OperationStatus operationStatus = OperationStatus.NEW;

    /**
     * 处理人
     */
    @Column(length = 20)
    private String operator;

    /**
     * 发货时间
     */
    private Timestamp deliveryTime;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<DeliveryOrderItem> getItems() {
        return items;
    }

    public void setItems(List<DeliveryOrderItem> items) {
        this.items = items;
    }

    public InOutType getInOutType() {
        return inOutType;
    }

    public void setInOutType(InOutType inOutType) {
        this.inOutType = inOutType;
    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public UserType getShipperType() {
        return shipperType;
    }

    public void setShipperType(UserType shipperType) {
        this.shipperType = shipperType;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public UserType getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(UserType recipientType) {
        this.recipientType = recipientType;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
