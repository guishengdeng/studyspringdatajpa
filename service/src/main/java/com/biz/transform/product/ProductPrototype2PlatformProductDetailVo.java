package com.biz.transform.product;

import com.biz.gbck.vo.product.frontend.PlatformProductDetailVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/1/23
 */
public class ProductPrototype2PlatformProductDetailVo implements Function<ProductPrototype, PlatformProductDetailVo> {
    @Nullable
    @Override
    public PlatformProductDetailVo apply(@Nullable ProductPrototype input) {
        PlatformProductDetailVo respVo = new PlatformProductDetailVo();
        respVo.setName(input.getProductName());
        //todo
        respVo.setFinalPrice(input.getFinalPrice(0));
        respVo.setMarketPrice(input.getMarketPrice());
        respVo.setProductCode(input.getProductCode());
        respVo.setSubTitle(input.getSubTitle());
        respVo.setSubTitle(input.getSubTitle());
        return null;
    }
}
