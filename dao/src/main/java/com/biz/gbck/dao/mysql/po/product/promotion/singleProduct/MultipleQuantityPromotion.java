package com.biz.gbck.dao.mysql.po.product.promotion.singleProduct;

import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import java.util.List;
import javax.persistence.*;

/**
 * 倍数特价
 * <p>
 * 例如：A商品原价100元/瓶，规定是3的倍数的商品半价，那么购买8瓶时，第1、2、4、5、7、8瓶100元/瓶，第3、6瓶50元/瓶，应付700元
 * 当购买商品的数量达到促销数量的整数倍时，这些逢整数倍的那一个商品将享受特价。
 * </p>
 * Created by david-liu on 2017/04/21 16:40.
 */
@Entity
@Table(name = "pro_promotion_multiple_quantity")
public class MultipleQuantityPromotion extends ProductPromotion {
    private static final long serialVersionUID = 7357545182892513869L;

    @OneToMany(mappedBy = "multipleQuantityPromotion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MultipleQuantityPromotionProduct> multipleQuantityPromotionProductList;

    public List<MultipleQuantityPromotionProduct> getMultipleQuantityPromotionProductList() {
        return multipleQuantityPromotionProductList;
    }

    public void setMultipleQuantityPromotionProductList(List<MultipleQuantityPromotionProduct> multipleQuantityPromotionProductList) {
        this.multipleQuantityPromotionProductList = multipleQuantityPromotionProductList;
    }
}
