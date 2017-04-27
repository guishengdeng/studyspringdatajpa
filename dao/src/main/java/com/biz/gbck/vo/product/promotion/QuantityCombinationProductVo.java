package com.biz.gbck.vo.product.promotion;

import java.io.Serializable;

/**
 * 数量组合商品Vo
 *
 * Created by david-liu on 2017/04/24 10:54.
 */
public class QuantityCombinationProductVo implements Serializable {
    private static final long serialVersionUID = -3704018309522105761L;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品参与促销最少购买数量
     */
    private Integer minPurchaseQuantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getMinPurchaseQuantity() {
        return minPurchaseQuantity;
    }

    public void setMinPurchaseQuantity(Integer minPurchaseQuantity) {
        this.minPurchaseQuantity = minPurchaseQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
