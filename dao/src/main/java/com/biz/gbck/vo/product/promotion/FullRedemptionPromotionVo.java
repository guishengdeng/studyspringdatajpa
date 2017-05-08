package com.biz.gbck.vo.product.promotion;

import java.io.Serializable;

/**
 * 满额换购促销规则Vo
 *
 * Created by david-liu on 2017/04/24 10:22.
 */
public class FullRedemptionPromotionVo implements Serializable {
    private static final long serialVersionUID = -7536796200390556835L;

    /**
     * 满足金额
     */
    private Integer accountLimit;

    /**
     * 赠品商品编码
     */
    private String giftProductCode;

    /**
     * 赠品数量
     */
    private Integer giftQuantity;

    /**
     * 赠品数量限制
     */
    private Integer totalGiftQuantityLimit;

    /**
     * 换购价
     */
    private Integer redemptionPrice;

    public Integer getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(Integer accountLimit) {
        this.accountLimit = accountLimit;
    }

    public String getGiftProductCode() {
        return giftProductCode;
    }

    public void setGiftProductCode(String giftProductCode) {
        this.giftProductCode = giftProductCode;
    }

    public Integer getGiftQuantity() {
        return giftQuantity;
    }

    public void setGiftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
    }

    public Integer getTotalGiftQuantityLimit() {
        return totalGiftQuantityLimit;
    }

    public void setTotalGiftQuantityLimit(Integer totalGiftQuantityLimit) {
        this.totalGiftQuantityLimit = totalGiftQuantityLimit;
    }

    public Integer getRedemptionPrice() {
        return redemptionPrice;
    }

    public void setRedemptionPrice(Integer redemptionPrice) {
        this.redemptionPrice = redemptionPrice;
    }
}
