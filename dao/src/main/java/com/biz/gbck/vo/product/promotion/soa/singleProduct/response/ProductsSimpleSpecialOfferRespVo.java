package com.biz.gbck.vo.product.promotion.soa.singleProduct.response;

import java.io.Serializable;
import java.util.Map;

/**
 * 商品简单特价RespVo
 *
 * Created by david-liu on 2017/04/27 14:51.
 */
public class ProductsSimpleSpecialOfferRespVo implements Serializable {
    private static final long serialVersionUID = 3830499310960562423L;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

    /**
     * 简单特价商品项Map
     */
    private Map<String, ProductsSimpleSpecialOfferItemVo> productCode2PromotionItemVoMap;

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public Map<String, ProductsSimpleSpecialOfferItemVo> getProductCode2PromotionItemVoMap() {
        return productCode2PromotionItemVoMap;
    }

    public void setProductCode2PromotionItemVoMap(Map<String, ProductsSimpleSpecialOfferItemVo> productCode2PromotionItemVoMap) {
        this.productCode2PromotionItemVoMap = productCode2PromotionItemVoMap;
    }
}
