package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import java.util.List;
import javax.persistence.*;

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
     * 单品买赠促销商品信息
     */
    @OneToMany(mappedBy = "singlePurchaseGiftPromotion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SinglePurchaseGiftPromotionProduct> singlePurchaseGiftPromotionProductList;

    public List<SinglePurchaseGiftPromotionProduct> getSinglePurchaseGiftPromotionProductList() {
        return singlePurchaseGiftPromotionProductList;
    }

    public void setSinglePurchaseGiftPromotionProductList(List<SinglePurchaseGiftPromotionProduct> singlePurchaseGiftPromotionProductList) {
        this.singlePurchaseGiftPromotionProductList = singlePurchaseGiftPromotionProductList;
    }
}
