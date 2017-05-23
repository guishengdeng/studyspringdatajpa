package com.biz.soa.promotion.vo;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.enums.product.promotion.CutPromotionTypeEnum;
import com.biz.gbck.vo.product.promotion.ReachCutPromotionVo;
import java.util.List;
import java.util.stream.Collectors;
import org.codelogger.utils.CollectionUtils;

/**
 * 组合满减促销VO
 *
 * Created by david-liu on 2017/05/22 09:34.
 */
public class CombinationAccountPromotionVO extends ProductPromotionVO {
    private static final long serialVersionUID = 1309816358674673805L;

    /**
     * 满减促销类型(倍增满减/阶梯满减)
     */
    private CutPromotionTypeEnum cutPromotionType;

    /**
     * 商品组合规则
     */
    private List<Long> combinationProductIds;

    /**
     * 促销规则
     */
    private List<ReachCutPromotionVo> promotionRules;

    public CombinationAccountPromotionVO() {
    }

    public CombinationAccountPromotionVO(CutPromotionTypeEnum cutPromotionType, List<Product> combinationProducts, List<ReachCutPromotionVo> promotionRules) {
        this.cutPromotionType = cutPromotionType;
        this.promotionRules = promotionRules;
        if (CollectionUtils.isNotEmpty(combinationProducts)) {
            this.combinationProductIds = combinationProducts.stream().map(Product::getId).collect(Collectors.toList());
        }
    }

    public CutPromotionTypeEnum getCutPromotionType() {
        return cutPromotionType;
    }

    public void setCutPromotionType(CutPromotionTypeEnum cutPromotionType) {
        this.cutPromotionType = cutPromotionType;
    }

    public List<Long> getCombinationProductIds() {
        return combinationProductIds;
    }

    public void setCombinationProductIds(List<Long> combinationProductIds) {
        this.combinationProductIds = combinationProductIds;
    }

    public List<ReachCutPromotionVo> getPromotionRules() {
        return promotionRules;
    }

    public void setPromotionRules(List<ReachCutPromotionVo> promotionRules) {
        this.promotionRules = promotionRules;
    }
}
