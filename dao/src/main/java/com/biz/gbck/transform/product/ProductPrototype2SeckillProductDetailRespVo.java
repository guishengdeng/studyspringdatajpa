package com.biz.gbck.transform.product;

import com.biz.gbck.vo.product.frontend.SeckillProductDetailRespVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.google.common.base.Function;

/**
 * 转换器(ProductPrototype --> SeckillProductDetailRespVo)
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class ProductPrototype2SeckillProductDetailRespVo implements Function<ProductPrototype, SeckillProductDetailRespVo> {
    @Override
    public SeckillProductDetailRespVo apply(ProductPrototype p) {
        SeckillProductDetailRespVo vo = new SeckillProductDetailRespVo();
        vo.setProductId(String.valueOf(p.getProductId()));
        vo.setProductCode(p.getProductCode());
        vo.setProductName(p.getProductName());
        vo.setSubTitle(p.getSubTitle());
        vo.setVendorId(String.valueOf(p.getVendorId()));
        vo.setProductType(p.getProductType());
        vo.setProductImages(p.getProductImages());
        vo.setMarketPrice(p.getMarketPrice());
        vo.setWeight(p.getWeight());
        vo.setBrandId(p.getBrandId() != null ? p.getBrandId().toString() : "");
        vo.setBrandName(p.getBrandName());
        vo.setCategoryId(p.getCategoryId() != null ? p.getCategoryId().toString() : "");
        vo.setCategoryName(p.getCategoryName());
        vo.setPredictArrival(p.getPredictArrival());
        return vo;
    }
}
