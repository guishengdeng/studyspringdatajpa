package com.biz.gbck.dao.mysql.po.product.promotion;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * 简单特价
 *
 * Created by david-liu on 2017/04/21 15:17.
 */
@Entity
@Table(name = "pro_promotion_simple_special_offer")
public class SimpleSpecialOfferPromotion extends ProductPromotion {
    private static final long serialVersionUID = 1898159488748554862L;

    /**
     * 商品
     */
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 促销价
     */
    @Column(nullable = false)
    private Integer promotionPrice;

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
}
