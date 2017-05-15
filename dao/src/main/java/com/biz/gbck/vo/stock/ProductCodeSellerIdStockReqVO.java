package com.biz.gbck.vo.stock;

import java.io.Serializable;

/**
 * 通过商品编码, 上级采购方ID获取库存请求VO
 *
 * Created by david-liu on 2017/05/02 17:59.
 */
public class ProductCodeSellerIdStockReqVO implements Serializable {
    private static final long serialVersionUID = -5905083372106286481L;

    /**
     * 商品编码
     */
    private Long productId;

    /**
     * 上级采购方ID
     */
    private Long sellerId;

    public ProductCodeSellerIdStockReqVO(Long productId, Long sellerId) {
        this.productId = productId;
        this.sellerId = sellerId;
    }

    public ProductCodeSellerIdStockReqVO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
