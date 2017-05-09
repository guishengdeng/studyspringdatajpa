package com.biz.soa.product.service.interfaces;

import com.biz.gbck.dao.redis.ro.product.price.PriceRO;
import com.biz.gbck.dao.redis.ro.product.promotion.SimpleSpecialOfferPromotionRO;

/**
 * 商品价格生成策略
 *
 * Created by david-liu on 2017/05/02 14:39.
 */
public interface ProductPriceGenerator {
    Integer getSalePrice();

    Integer getSuggestPrice();

    Integer getDynamicAveragePrice();

    Integer getPurchasePrice();

    void setPriceRO(PriceRO priceRO);

    void setSimpleSpecialOfferPromotionRO(SimpleSpecialOfferPromotionRO simpleSpecialOfferPromotionRO);
}
