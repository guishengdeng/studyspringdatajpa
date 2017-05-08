package com.biz.soa.product.service.interfaces.impl;

import com.biz.gbck.dao.redis.ro.product.price.PriceRO;
import com.biz.gbck.dao.redis.ro.product.promotion.SimpleSpecialOfferPromotionRO;
import com.biz.soa.product.service.interfaces.ProductPriceGenerator;

/**
 * 隔壁仓库价格生成策略
 * Created by david-liu on 2017/05/02 14:43.
 */
public class DepotNextDoorPriceGenerator implements ProductPriceGenerator {

    private PriceRO priceRO;

    private SimpleSpecialOfferPromotionRO simpleSpecialOfferPromotionRO;

    @Override
    public Integer getSalePrice() {
        if (simpleSpecialOfferPromotionRO != null) {
            return Math.min(simpleSpecialOfferPromotionRO.getPromotionPrice(), this.priceRO.getSalePrice());
        }
        return priceRO.getSalePrice();
    }

    @Override
    public Integer getSuggestPrice() {
        return this.priceRO.getSuggestPrice();
    }

    @Override
    public Integer getDynamicAveragePrice() {
        return this.priceRO.getDynamicAveragePrice();
    }

    @Override
    public Integer getPurchasePrice() {
        return this.priceRO.getPurchasePrice();
    }

    public void setPriceRO(PriceRO priceRO) {
        this.priceRO = priceRO;
    }

    public void setSimpleSpecialOfferPromotionRO(SimpleSpecialOfferPromotionRO simpleSpecialOfferPromotionRO) {
        this.simpleSpecialOfferPromotionRO = simpleSpecialOfferPromotionRO;
    }
}
