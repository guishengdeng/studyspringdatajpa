package com.biz.gbck.dao.mysql.po.product.promotion.redemption;

import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 换购促销
 *
 * <p>
 * 当购买商品达到一定数量后加多少元换购另外的商品（换购商品只能是一种）。例如买A商品1瓶、B商品两瓶加10元可换购C商品。
 * </p>
 *
 * Created by david-liu on 2017/04/24 11:16.
 */
@Entity
@Table(name = "pro_promotion_redemption")
public class RedemptionPromotion extends ProductPromotion {
    private static final long serialVersionUID = -6680839018318787701L;

    /**
     * 换购商品
     */
    @Column(columnDefinition = "TEXT")
    private String redemptionProducts;

    /**
     * 购买商品
     */
    @Column(columnDefinition = "TEXT")
    private String purchaseProducts;

    public String getRedemptionProducts() {
        return redemptionProducts;
    }

    public void setRedemptionProducts(String redemptionProducts) {
        this.redemptionProducts = redemptionProducts;
    }

    public String getPurchaseProducts() {
        return purchaseProducts;
    }

    public void setPurchaseProducts(String purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }
}
