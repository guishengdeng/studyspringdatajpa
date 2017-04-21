package com.biz.gbck.dao.mysql.po.product.master;

import com.biz.gbck.enums.CommonActionEnum;
import com.biz.support.jpa.po.BaseEntity;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * 商品维护记录
 *
 * Created by david-liu on 2017/04/21 10:38.
 */
@Entity
@Table(name = "pro_product_action_log")
public class ProductActionLog extends BaseEntity {
    private static final long serialVersionUID = 3035599942359261091L;

    /**
     * 操作商品
     */
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 操作时间
     */
    @Column(nullable = false)
    private Timestamp actionTime;

    /**
     * 操作人
     */
    @Column(length = 50)
    private String actor;

    /**
     * 操作行为
     */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CommonActionEnum action;

    /**
     * 操作之前的商品信息(存JSON字符串)
     */
    @Column(columnDefinition = "TEXT")
    private String infoBeforeAction;

    /**
     * 操作之后的商品信息(存JSON字符串)
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String infoAfterAction;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Timestamp getActionTime() {
        return actionTime;
    }

    public void setActionTime(Timestamp actionTime) {
        this.actionTime = actionTime;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getInfoBeforeAction() {
        return infoBeforeAction;
    }

    public void setInfoBeforeAction(String infoBeforeAction) {
        this.infoBeforeAction = infoBeforeAction;
    }

    public String getInfoAfterAction() {
        return infoAfterAction;
    }

    public void setInfoAfterAction(String infoAfterAction) {
        this.infoAfterAction = infoAfterAction;
    }

    public CommonActionEnum getAction() {
        return action;
    }

    public void setAction(CommonActionEnum action) {
        this.action = action;
    }
}
