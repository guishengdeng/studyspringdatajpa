package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 简单特价
 * <p>商品直接降价销售，例如：A商品原价100元/瓶，特价50元/瓶</p>
 *
 * Created by david-liu on 2017/04/21 15:17.
 */
@Entity
@Table(name = "pro_promotion_simple_special_offer")
public class SimpleSpecialOfferPromotion extends ProductPromotion {
    private static final long serialVersionUID = 1898159488748554862L;

    /**
     * 商品编码
     */
    @Column(length = 50)
    private String productCode;

    /**
     * 促销价
     */
    @Column(nullable = false)
    private Integer promotionPrice;

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
}
