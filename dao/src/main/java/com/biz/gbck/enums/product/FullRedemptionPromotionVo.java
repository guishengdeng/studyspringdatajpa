package com.biz.gbck.enums.product;

import java.io.Serializable;

/**
 * 满额换购促销规则Vo
 *
 * Created by david-liu on 2017/04/24 10:22.
 */
public class FullRedepmtionPromotionVo implements Serializable {
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
}
