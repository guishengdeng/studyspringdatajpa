package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 单品买赠促销
 * <p>
 * 单品特价买赠，比如原价100元/瓶，现可以80元/瓶情况下买3瓶赠1瓶
 * </p>
 *
 * Created by david-liu on 2017/04/21 16:29.
 */
@Entity
@Table(name = "pro_promotion_single_purchase_gift")
public class SinglePurchaseGiftPromotion extends ProductPromotion {
    private static final long serialVersionUID = -5342738534723353991L;

    /**
     * 商品
     */
    @Column(length = 50)
    private String productCode;

    /**
     * 促销价
     */
    @Column(nullable = false)
    private Integer promotionPrice;

    /**
     * 达标数量
     */
    @Column(nullable = false)
    private Integer quantityLimit;

    /**
     * 赠送数量
     */
    @Column(nullable = false)
    private Integer giftQuantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public Integer getGiftQuantity() {
        return giftQuantity;
    }

    public void setGiftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
    }
}
