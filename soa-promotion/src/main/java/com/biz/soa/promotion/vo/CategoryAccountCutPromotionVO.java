package com.biz.soa.promotion.vo;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.enums.product.promotion.CutPromotionTypeEnum;
import com.biz.gbck.vo.product.promotion.ReachCutPromotionVo;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.codelogger.utils.CollectionUtils;

/**
 * 分类满减促销VO
 *
 * Created by david-liu on 2017/05/22 09:27.
 */
public class CategoryAccountCutPromotionVO extends ProductPromotionVO {
    private static final long serialVersionUID = 721447396362891214L;

    /**
     * 满减促销类型(倍增满减/阶梯满减)
     */
    private CutPromotionTypeEnum cutPromotionType;

    /**
     * 促销规则
     */
    private List<ReachCutPromotionVo> promotionRules;

    /**
     * 分类ID
     */
    private List<Long> categoryIds;

    public CategoryAccountCutPromotionVO() {
    }

    public CategoryAccountCutPromotionVO(CutPromotionTypeEnum cutPromotionType, List<Category> categories, List<ReachCutPromotionVo> promotionRules) {
        this.cutPromotionType = cutPromotionType;
        if (Objects.nonNull(categories)) {
            this.categoryIds = categories.stream().map(Category::getId).collect(Collectors.toList());
        }

        if (CollectionUtils.isNotEmpty(promotionRules)) {
            this.promotionRules = promotionRules;
        }
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

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
