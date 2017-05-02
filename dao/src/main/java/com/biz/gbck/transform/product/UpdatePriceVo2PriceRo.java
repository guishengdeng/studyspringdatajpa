package com.biz.gbck.transform.product;

import com.biz.gbck.dao.redis.ro.product.bbc.PriceRo;
import com.biz.gbck.vo.product.backend.UpdatePriceVo;
import com.google.common.base.Function;
import java.math.BigDecimal;

/**
 * @author 江南
 * @date 2017/1/18
 * @reviewer
 */
public class UpdatePriceVo2PriceRo implements Function<UpdatePriceVo, PriceRo> {
    @Override
    public PriceRo apply(UpdatePriceVo input) {
        PriceRo priceRo = new PriceRo();
        Integer finalPrice = new BigDecimal(input.getFinalPrice()).multiply(new BigDecimal(100)).intValue();
        priceRo.setProductCode(input.getProductCode());
        priceRo.setMinPrice(input.getMinPrice());
        priceRo.setCostPrice(input.getCostPrice());
        priceRo.setFinalPrice(finalPrice);
        priceRo.setMarketPrice(new BigDecimal(input.getMarketPrice()).multiply(new BigDecimal(100)).intValue());
        priceRo.setPrice1(finalPrice);
        priceRo.setPrice2(finalPrice);
        priceRo.setPrice3(finalPrice);
        priceRo.setPrice4(finalPrice);
        priceRo.setPrice5(finalPrice);
        priceRo.setPrice6(finalPrice);
        priceRo.setPrice7(finalPrice);
        priceRo.setPrice8(finalPrice);
        priceRo.setPrice9(finalPrice);
        priceRo.setPrice10(finalPrice);
        priceRo.setPrice11(finalPrice);
        priceRo.setPrice12(finalPrice);
        priceRo.setPrice13(finalPrice);
        priceRo.setPrice14(finalPrice);
        priceRo.setPrice15(finalPrice);
        priceRo.setPrice16(finalPrice);
        priceRo.setPrice17(finalPrice);
        priceRo.setPrice18(finalPrice);
        priceRo.setPrice19(finalPrice);
        priceRo.setPrice20(finalPrice);
        return priceRo;
    }
}
