package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 单品买赠商品
 *
 * Created by david-liu on 2017/04/27 11:38.
 */
@Entity
@Table(name = "pro_promotion_single_purchase_gift_product")
public class SinglePurchaseGiftPromotionProduct extends BaseEntity {
    private static final long serialVersionUID = -5503181577440335761L;

    /**
     * 单品买赠促销信息
     */
    @ManyToOne
    @JoinColumn(name = "single_purchase_gift_promotion_id")
    private SinglePurchaseGiftPromotion singlePurchaseGiftPromotion;

    /**
     * 商品信息
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 达标数量限制
     */
    @Column
    private Integer quantityLimit;

    /**
     * 赠品数量
     */
    @Column
    private Integer giftQuantity;

    /**
     * 赠品数量限制
     */
    @Column
    private Integer giftQuantityLimit;

    /**
     * 是否限制赠品库存
     */
    @Column
    private Boolean isLimitStock = Boolean.TRUE;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantityLimit() {
        return quantityLimit;
    }

    public void setQuantityLimit(Integer quantityLimit) {
        this.quantityLimit = quantityLimit;
    }

    public Integer getGiftQuantity() {
        return giftQuantity;
    }

    public void setGiftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
    }

    public Integer getGiftQuantityLimit() {
        return giftQuantityLimit;
    }

    public void setGiftQuantityLimit(Integer giftQuantityLimit) {
        this.giftQuantityLimit = giftQuantityLimit;
    }

    public SinglePurchaseGiftPromotion getSinglePurchaseGiftPromotion() {
        return singlePurchaseGiftPromotion;
    }

    public void setSinglePurchaseGiftPromotion(SinglePurchaseGiftPromotion singlePurchaseGiftPromotion) {
        this.singlePurchaseGiftPromotion = singlePurchaseGiftPromotion;
    }

    public Boolean getLimitStock() {
        return isLimitStock;
    }

    public void setLimitStock(Boolean limitStock) {
        isLimitStock = limitStock;
    }
}
