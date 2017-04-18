package com.biz.transform.product;

import com.biz.gbck.dao.redis.ro.product.PriceRo;
import com.biz.gbck.vo.product.backend.InitTypeAPriceVo;
import com.google.common.base.Function;

import static com.biz.gbck.dao.redis.repository.product.SaleAreaRedisDao.TYPE_A_PRICE_AREA_NO;


/**
 * 转换器(InitTypeAPriceVo --> PriceRo)
 *
 * @author david-liu
 * @date 2017年02月23日
 * @reviewer
 */
public class InitTypeAPriceVo2PriceRo implements Function<InitTypeAPriceVo, PriceRo> {
    @Override
    public PriceRo apply(InitTypeAPriceVo vo) {
        PriceRo ro = new PriceRo();
        Integer price = vo.getFinalPrice();
        ro.setId(String.format("%s%s", vo.getProductCode(), TYPE_A_PRICE_AREA_NO));
        ro.setCostPrice(vo.getMarketPrice());
        ro.setMarketPrice(vo.getMarketPrice());
        ro.setAreaCode(TYPE_A_PRICE_AREA_NO);
        ro.setFinalPrice(price);
        ro.setPrice1(price);
        ro.setPrice2(price);
        ro.setPrice3(price);
        ro.setPrice4(price);
        ro.setPrice5(price);
        ro.setPrice6(price);
        ro.setPrice7(price);
        ro.setPrice8(price);
        ro.setPrice9(price);
        ro.setPrice10(price);
        ro.setPrice11(price);
        ro.setPrice12(price);
        ro.setPrice13(price);
        ro.setPrice14(price);
        ro.setPrice15(price);
        ro.setPrice16(price);
        ro.setPrice17(price);
        ro.setPrice18(price);
        ro.setPrice19(price);
        ro.setPrice20(price);
        return ro;
    }
}
