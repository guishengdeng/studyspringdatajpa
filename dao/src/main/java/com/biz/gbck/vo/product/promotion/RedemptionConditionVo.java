package com.biz.gbck.vo.product.promotion;

import java.io.Serializable;

/**
 * 换购促销条件Vo
 *
 * Created by david-liu on 2017/04/24 11:27.
 */
public class RedemptionConditionVo implements Serializable {
    private static final long serialVersionUID = 1214676299151852894L;

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 达标数量
     */
    private Integer quantityLimit;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantityLimit() {
        return quantityLimit;
    }

    public void setQuantityLimit(Integer quantityLimit) {
        this.quantityLimit = quantityLimit;
    }
}
