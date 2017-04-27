package com.biz.gbck.vo.product.promotion;

import java.io.Serializable;

/**
 * 阶梯满赠规则Vo
 *
 * <p>
 * 每满X元赠Y瓶酒（赠酒只能是同一种酒），例如：A商品每满100元赠1瓶B，A商品每满200元赠3瓶B，依次类推。
 * </p>
 *
 * Created by david-liu on 2017/04/24 09:44.
 */
public class StairGiftPromotionRuleVo implements Serializable {

    private static final long serialVersionUID = -6254530022434644101L;

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
}
