package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 批量特价商品
 *
 * Created by david-liu on 2017/04/26 15:02.
 */
@Entity
@Table(name = "pro_promotion_batch_special_offer_product")
public class BatchSpecialOfferPromotionProduct extends BaseEntity {
    private static final long serialVersionUID = 5270063575792467221L;

    /**
     * 批量特价促销信息
     */
    @ManyToOne
    @JoinColumn(name = "batch_special_offer_promotion_id")
    private BatchSpecialOfferPromotion batchSpecialOfferPromotion;

    /**
     * 促销商品信息
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
     * 促销达标数量
     */
    @Column
    private Integer quantityLimit;

    public BatchSpecialOfferPromotion getBatchSpecialOfferPromotion() {
        return batchSpecialOfferPromotion;
    }

    public void setBatchSpecialOfferPromotion(BatchSpecialOfferPromotion batchSpecialOfferPromotion) {
        this.batchSpecialOfferPromotion = batchSpecialOfferPromotion;
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

    public Integer getQuantityLimit() {
        return quantityLimit;
    }

    public void setQuantityLimit(Integer quantityLimit) {
        this.quantityLimit = quantityLimit;
    }
}
