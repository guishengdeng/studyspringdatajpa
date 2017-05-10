package com.biz.gbck.vo.product.promotion.soa.singleProduct.response;

import java.io.Serializable;
import java.util.List;

/**
 * 商品的单品促销响应Vo
 *
 * Created by david-liu on 2017/04/27 11:22.
 */
public class ProductSingleProductPromotionRespVo implements Serializable {
    private static final long serialVersionUID = -1393822710956089931L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 单品促销促销项
     */
    private List<SingleProductPromotionRespItemVo> singleProductPromotionItems;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<SingleProductPromotionRespItemVo> getSingleProductPromotionItems() {
        return singleProductPromotionItems;
    }

    public void setSingleProductPromotionItems(List<SingleProductPromotionRespItemVo> singleProductPromotionItems) {
        this.singleProductPromotionItems = singleProductPromotionItems;
    }
}
