package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 数量特价商品
 *
 * Created by david-liu on 2017/04/26 15:24.
 */
@Entity
@Table(name = "pro_promotion_quantity_special_offer_product")
public class QuantitySpecialOfferPromotionProduct extends BaseEntity {
    private static final long serialVersionUID = 2617980727041268786L;

    /**
     * 数量特价信息
     */
    @ManyToOne
    @JoinColumn(name = "quantity_special_offer_promotion_id")
    private QuantitySpecialOfferPromotion quantitySpecialOfferPromotion;

    /**
     * 商品信息
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 特价
     */
    @Column
    private Integer promotionPrice;

    /**
     * 数量
     */
    @Column
    private Integer quantity;

    public QuantitySpecialOfferPromotion getQuantitySpecialOfferPromotion() {
        return quantitySpecialOfferPromotion;
    }

    public void setQuantitySpecialOfferPromotion(QuantitySpecialOfferPromotion quantitySpecialOfferPromotion) {
        this.quantitySpecialOfferPromotion = quantitySpecialOfferPromotion;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
