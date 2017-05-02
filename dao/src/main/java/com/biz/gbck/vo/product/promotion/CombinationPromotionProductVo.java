package com.biz.gbck.vo.product.promotion;

import java.io.Serializable;

/**
 * 组合促销商品Vo
 *
 * Created by david-liu on 2017/04/24 10:48.
 */
public class CombinationPromotionProductVo implements Serializable {
    private static final long serialVersionUID = -1521870359637397995L;

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 组合商品促销商品编码
     */
    private String productCode;

    /**
     * 需要购买的数量
     */
    private Integer quantityLimit;

    /**
     * 组合价
     */
    private Integer combinationPrice;

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

    public Integer getCombinationPrice() {
        return combinationPrice;
    }

    public void setCombinationPrice(Integer combinationPrice) {
        this.combinationPrice = combinationPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
