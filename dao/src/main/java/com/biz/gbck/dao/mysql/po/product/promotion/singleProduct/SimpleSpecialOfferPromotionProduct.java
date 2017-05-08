package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 简单特价商品
 *
 * Created by david-liu on 2017/04/26 14:49.
 */
@Entity
@Table(name = "pro_promotion_simple_special_offer_product")
public class SimpleSpecialOfferPromotionProduct extends BaseEntity {
    private static final long serialVersionUID = -9125908557476187236L;

    /**
     * 简单特价促销信息
     */
    @ManyToOne
    @JoinColumn(name = "simple_special_offer_promotion_id")
    private SimpleSpecialOfferPromotion simpleSpecialOfferPromotion;

    /**
     * 简单特价商品
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 特价
     */
    @Column
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

    public SimpleSpecialOfferPromotion getSimpleSpecialOfferPromotion() {
        return simpleSpecialOfferPromotion;
    }

    public void setSimpleSpecialOfferPromotion(SimpleSpecialOfferPromotion simpleSpecialOfferPromotion) {
        this.simpleSpecialOfferPromotion = simpleSpecialOfferPromotion;
    }
}
