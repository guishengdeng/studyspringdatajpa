package com.biz.gbck.vo.product.promotion.soa.singleProduct.response;

import java.io.Serializable;

/**
 * 商品简单特价Vo
 *
 * Created by david-liu on 2017/04/27 15:04.
 */
public class ProductSimpleSpecialOfferRespVo implements Serializable {
    private static final long serialVersionUID = -8764185455456711886L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 特价
     */
    private Integer promotionPrice;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Integer promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
