package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * 倍数特价商品
 *
 * Created by david-liu on 2017/04/26 15:16.
 */
@Entity
@Table(name = "pro_promotion_multiple_quantity_product")
public class MultipleQuantityPromotionProduct extends BaseEntity {
    private static final long serialVersionUID = 1201321249868373815L;

    /**
     * 倍数特价促销信息
     */
    @ManyToOne
    @JoinColumn(name = "multiple_quantity_promotion_id")
    private MultipleQuantityPromotion multipleQuantityPromotion;

    /**
     * 参与促销的商品
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
     * 倍数
     */
    @Column
    private Integer luckyNumber;

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

    public Integer getLuckyNumber() {
        return luckyNumber;
    }

    public void setLuckyNumber(Integer luckyNumber) {
        this.luckyNumber = luckyNumber;
    }

    public MultipleQuantityPromotion getMultipleQuantityPromotion() {
        return multipleQuantityPromotion;
    }

    public void setMultipleQuantityPromotion(MultipleQuantityPromotion multipleQuantityPromotion) {
        this.multipleQuantityPromotion = multipleQuantityPromotion;
    }
}
