package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import java.util.List;
import javax.persistence.*;

/**
 * 数量特价
 * <p>
 * 购买商品的数量达到促销数量的整数倍时，这些促销数量的整数倍的商品将享受特价。
 * 例如：A商品原价100元/瓶，规定当满足3的商品倍数享受特价80元/瓶，当购买8个商品时，
 * 第1、2、3、4、5、6个80元/瓶，第7、8个商品100元/瓶，应付680元
 * </p>
 *
 * Created by david-liu on 2017/04/21 16:46.
 */
@Table
@Entity(name = "pro_promotion_quantity_special_offer")
public class QuantitySpecialOfferPromotion extends ProductPromotion {
    private static final long serialVersionUID = -1051116366729922357L;

    @OneToMany(mappedBy = "quantitySpecialOfferPromotion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuantitySpecialOfferPromotionProduct> quantitySpecialOfferPromotionProductList;

    public List<QuantitySpecialOfferPromotionProduct> getQuantitySpecialOfferPromotionProductList() {
        return quantitySpecialOfferPromotionProductList;
    }

    public void setQuantitySpecialOfferPromotionProductList(List<QuantitySpecialOfferPromotionProduct> quantitySpecialOfferPromotionProductList) {
        this.quantitySpecialOfferPromotionProductList = quantitySpecialOfferPromotionProductList;
    }
}
