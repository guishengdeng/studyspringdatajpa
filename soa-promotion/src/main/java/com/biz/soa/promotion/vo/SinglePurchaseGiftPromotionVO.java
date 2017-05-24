package com.biz.soa.promotion.vo;

/**
 * 单品买赠促销VO
 *
 * Created by david-liu on 2017/05/22 09:08.
 */
public class SinglePurchaseGiftPromotionVO extends ProductPromotionVO {
    private static final long serialVersionUID = -3584196423573529476L;

    /**
     * 数量限制
     */
    private Integer quantityLimit;

    /**
     * 赠品数量
     */
    private Integer giftQuantity;

    /**
     * 赠品数量限制
     */
    private Integer giftQuantityLimit;

    /**
     * 赠品商品ID
     */
    private Long giftProductId;

    /**
     * 是否限制赠品库存
     */
    private Boolean isLimitStock;

    public Integer getQuantityLimit() {
        return quantityLimit;
    }

    public void setQuantityLimit(Integer quantityLimit) {
        this.quantityLimit = quantityLimit;
    }

    public Integer getGiftQuantity() {
        return giftQuantity;
    }

    public void setGiftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
    }

    public Integer getGiftQuantityLimit() {
        return giftQuantityLimit;
    }

    public void setGiftQuantityLimit(Integer giftQuantityLimit) {
        this.giftQuantityLimit = giftQuantityLimit;
    }

    public Long getGiftProductId() {
        return giftProductId;
    }

    public void setGiftProductId(Long giftProductId) {
        this.giftProductId = giftProductId;
    }

    public Boolean getLimitStock() {
        return isLimitStock;
    }

    public void setLimitStock(Boolean limitStock) {
        isLimitStock = limitStock;
    }
}
