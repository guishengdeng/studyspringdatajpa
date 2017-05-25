package com.biz.soa.promotion.vo;

import com.biz.gbck.enums.product.promotion.CutPromotionTypeEnum;
import com.biz.gbck.vo.product.promotion.ReachCutPromotionVo;
import java.util.List;

/**
 * 整单满减促销VO
 *
 * Created by david-liu on 2017/05/22 16:46.
 */
public class WholeOrderAccountCutPromotionVO extends ProductPromotionVO {
    private static final long serialVersionUID = 2921927464679527844L;

    /**
     * 满减促销类型(倍增满减/阶梯满减)
     */
    private CutPromotionTypeEnum cutPromotionType;

    /**
     * 促销规则
     */
    private List<ReachCutPromotionVo> promotionRules;

    public WholeOrderAccountCutPromotionVO() {
    }

    public WholeOrderAccountCutPromotionVO(CutPromotionTypeEnum cutPromotionType, List<ReachCutPromotionVo> promotionRules) {
        this.cutPromotionType = cutPromotionType;
        this.promotionRules = promotionRules;
    }

    public CutPromotionTypeEnum getCutPromotionType() {
        return cutPromotionType;
    }

    public void setCutPromotionType(CutPromotionTypeEnum cutPromotionType) {
        this.cutPromotionType = cutPromotionType;
    }

    public List<ReachCutPromotionVo> getPromotionRules() {
        return promotionRules;
    }

    public void setPromotionRules(List<ReachCutPromotionVo> promotionRules) {
        this.promotionRules = promotionRules;
    }
}
