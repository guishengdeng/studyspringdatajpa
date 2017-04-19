package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/28
 */
public class ProductSalesVo implements Serializable, Comparable<ProductSalesVo> {

    private static final long serialVersionUID = -3738104568925059518L;

    private String productCode;

    private Long productId;

    private Long quantity;

    private Long finalPrice;

    private Long categoryId;

    public ProductSalesVo(String productCode, Long productId, Long quantity, Long finalPrice, Long categoryId) {
        this.productCode = productCode;
        this.productId = productId;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
        this.categoryId = categoryId;
    }

    public ProductSalesVo() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int compareTo(ProductSalesVo o) {
        if (o == null) {
            return 1;
        }
        if (this.getQuantity() > o.getQuantity()) {
            return 1;
        } else if (this.getQuantity() == o.getQuantity()) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "ProductSalesVo{" +
                "productCode='" + productCode + '\'' +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", finalPrice=" + finalPrice +
                ", categoryId=" + categoryId +
                '}';
    }
}
