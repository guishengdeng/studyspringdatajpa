package com.biz.gbck.vo.product.promotion;

import com.biz.gbck.enums.product.promotion.CutPromotionTypeEnum;
import com.biz.gbck.enums.product.promotion.PromotionTypeEnum;

/**
 * 促销通知消息VO
 *
 * Created by david-liu on 2017/05/22 17:42.
 */
public class PromotionNoticeVO {
    private PromotionTypeEnum promotionType;

    private CutPromotionTypeEnum cutPromotionType;

    public PromotionTypeEnum getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionTypeEnum promotionType) {
        this.promotionType = promotionType;
    }

    public CutPromotionTypeEnum getCutPromotionType() {
        return cutPromotionType;
    }

    public void setCutPromotionType(CutPromotionTypeEnum cutPromotionType) {
        this.cutPromotionType = cutPromotionType;
    }
}
