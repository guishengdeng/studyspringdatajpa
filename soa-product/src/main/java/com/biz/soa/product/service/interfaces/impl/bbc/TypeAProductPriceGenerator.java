package com.biz.soa.product.service.interfaces.impl.bbc;

import com.biz.gbck.dao.redis.ro.product.bbc.PriceRo;
import com.biz.soa.product.service.interfaces.bbc.ProductPriceGenerator;

/**
 * A类商品价格生成器
 *
 * @author david-liu
 * @date 2017年02月14日
 * @reviewer
 */
public class TypeAProductPriceGenerator implements ProductPriceGenerator {

    private PriceRo priceRo;

    private TypeAProductPriceGenerator(PriceRo priceRo) {
        this.priceRo = priceRo;
    }

    public static ProductPriceGenerator doClone(PriceRo priceRo) {
        return new TypeAProductPriceGenerator(priceRo);
    }

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
