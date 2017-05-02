package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import javax.persistence.*;

/**
 * 批量特价
 * <p>
 * 购买某种商品达到规定数量后，该商品特价销售，例如：A商品原价100元/瓶，规定购买A商品10个（包含10个）以上时，
 * 每个A商品都是90元/瓶，那么购买9个时，每个100元，应付900元，购买10个时，每个90元，应付900元
 * </p>
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
    @ManyToOne
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
