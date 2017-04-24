package com.biz.gbck.dao.mysql.po.product.promotion;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * 数量特价
 *
 * Created by david-liu on 2017/04/21 16:46.
 */
@Table
@Entity(name = "pro_promotion_quantity_special_offer")
public class QuantitySpecialOfferPromotion extends ProductPromotion {
    private static final long serialVersionUID = -1051116366729922357L;

    /**
     * 商品
     */
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 促销价
     */
    @Column(nullable = false)
    private Integer promotionPrice;

    /**
     * 数量限制
     * <p>
     * 购买商品的数量达到促销数量的整数倍时，这些促销数量的整数倍的商品将享受特价。
     * 例如：A商品原价100元/瓶，规定当满足3的商品倍数享受特价80元/瓶，当购买8个商品时，
     * 第1、2、3、4、5、6个80元/瓶，第7、8个商品100元/瓶，应付680元
     * </p>
     */
    @Column(nullable = false)
    private Integer quantityLimit;
}
