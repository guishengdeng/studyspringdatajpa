package com.biz.gbck.vo.stock;

import java.io.Serializable;

/**
 * 商品库存VO
 *
 * Created by david-liu on 2017/05/02 11:45.
 */
public class ProductStockVO implements Serializable {
    private static final long serialVersionUID = 5264457748831500076L;

    private Long productId;

    private Long sellerId;

    private String productCode;

    private Integer stock;

    public ProductStockVO(String productCode, Integer stock) {
        this.productCode = productCode;
        this.stock = stock;
    }

    public ProductStockVO() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    @Override
    public String toString() {
        return "ProductStockVO{" +
                "productCode='" + productCode + '\'' +
                ", stock=" + stock +
                '}';
    }
}
