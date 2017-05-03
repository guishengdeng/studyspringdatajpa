package com.biz.soa.product.service.interfaces.bbc;

/**
 * 商品价格生成器
 * <pre>
 *     将商品的取价逻辑抽象出来
 * </pre>
 *
 * @author david-liu
 * @date 2017年02月14日
 * @reviewer
 */
public interface ProductPriceGenerator {

    Integer doGetFinalPrice(Integer userLevel);

    Integer doGetMarketPrice();

}
