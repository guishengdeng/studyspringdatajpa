package com.biz.gbck.vo.product.promotion.soa.singleProduct.response;

import com.biz.gbck.enums.product.promotion.PromotionTypeEnum;
import com.biz.gbck.vo.product.promotion.IProductPromotionVo;

/**
 * 生效的促销项Vo
 *
 * Created by david-liu on 2017/04/27 15:18.
 */
public class EffectPromotionCutItemVo implements IProductPromotionVo {
    private static final long serialVersionUID = -8625028312455291770L;

    @Override
    public PromotionTypeEnum getPromotionType() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Long getPromotionId() {
        return null;
    }

    @Override
    public Integer getCutAccount() {
        return null;
    }
}
