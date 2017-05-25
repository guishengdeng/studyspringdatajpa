package com.biz.gbck.dao.mysql.po.order;

import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;

/**
 * 订单使用的促销活动
 *
 * @author lei
 * @date 2017年05月20日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "ord_order_promotion")
public class OrderPromotion extends BaseEntity {

    private static final long serialVersionUID = 3603129183286221005L;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    protected Order order;

    //优惠活动id
    @Column(nullable = false)
    private Long promotionId;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }
}
