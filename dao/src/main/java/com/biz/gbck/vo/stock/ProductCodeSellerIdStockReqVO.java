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
    private String productCode;

    /**
     * 上级采购方ID
     */
    private Long sellerId;

    public ProductCodeSellerIdStockReqVO(String productCode, Long sellerId) {
        this.productCode = productCode;
        this.sellerId = sellerId;
    }

    public ProductCodeSellerIdStockReqVO() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
