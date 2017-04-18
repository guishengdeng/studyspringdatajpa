package com.biz.soa.product.service.interfaces.impl;

import com.biz.gbck.dao.redis.ro.product.PriceRo;
import com.biz.soa.product.service.interfaces.ProductPriceGenerator;

/**
 * A类商品价格生成器
 *
 * @author david-liu
 * @date 2017年02月14日
 * @reviewer
 */
public class TypeAProductPriceGenerator implements ProductPriceGenerator {

    public static ProductPriceGenerator doClone(PriceRo priceRo) {
        return new TypeAProductPriceGenerator(priceRo);
    }

    private TypeAProductPriceGenerator(PriceRo priceRo) {
        this.priceRo = priceRo;
    }

    private PriceRo priceRo;

    @Override
    public Integer doGetFinalPrice(Integer userLevel) {
        if (priceRo == null) {
            return null;
        }
        return this.priceRo.getPriceByLevel(userLevel);
    }

    @Override
    public Integer doGetMarketPrice() {
        if (priceRo == null) {
            return null;
        }
        return this.priceRo.getMarketPrice();
    }
}
