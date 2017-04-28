package com.biz.gbck.vo.product.promotion.soa.singleProduct.response;

import java.io.Serializable;

/**
 * 商品简单特价商品特价项Vo
 *
 * Created by david-liu on 2017/04/27 14:52.
 */
public class ProductsSimpleSpecialOfferItemVo implements Serializable {
    private static final long serialVersionUID = 4646939430537363026L;

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
