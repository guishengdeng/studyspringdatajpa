package com.biz.gbck.dao.mysql.po.product.promotion;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * 批量特价
 *
 * Created by david-liu on 2017/04/21 16:09.
 */
@Entity
@Table(name = "pro_promotion_batch_special_offer")
public class BatchSpecialOfferPromotion extends ProductPromotion {
    private static final long serialVersionUID = -1624861972742974525L;

    /**
     * 商品信息
     */
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 促销价
     */
    @Column(nullable = false)
    private Integer promotionPrice;

    /**
     * 数量限制
     */
    @Column(nullable = false)
    private Integer quantityLimit;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Integer promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getQuantityLimit() {
        return quantityLimit;
    }

    public void setQuantityLimit(Integer quantityLimit) {
        this.quantityLimit = quantityLimit;
    }
}
