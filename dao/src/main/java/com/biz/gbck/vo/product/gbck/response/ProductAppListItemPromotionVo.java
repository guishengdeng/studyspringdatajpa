package com.biz.gbck.vo.product.gbck.response;

import java.io.Serializable;

/**
 * App商品列表项促销Vo
 *
 * Created by david-liu on 2017/04/28 09:24.
 */
public class ProductAppListItemPromotionVo implements Serializable {
    private static final long serialVersionUID = 4124211287090873965L;

    /**
     * 促销名称
     */
    private String promotionName;

    /**
     * 促销Id
     */
    private Long promotionId;

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }
}
