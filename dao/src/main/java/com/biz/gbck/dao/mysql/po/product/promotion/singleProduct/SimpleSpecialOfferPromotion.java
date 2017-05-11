package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import java.util.List;
import javax.persistence.*;

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
     * 简单特价商品
     */
    @OneToMany(mappedBy = "simpleSpecialOfferPromotion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SimpleSpecialOfferPromotionProduct> promotionProductList;

    public List<SimpleSpecialOfferPromotionProduct> getPromotionProductList() {
        return promotionProductList;
    }

    public void setPromotionProductList(List<SimpleSpecialOfferPromotionProduct> promotionProductList) {
        this.promotionProductList = promotionProductList;
    }
}
