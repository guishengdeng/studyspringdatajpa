package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.vo.product.promotion.ReachCutPromotionVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.*;
import org.codelogger.utils.StringUtils;

/**
 * 分类阶梯满减促销
 *
 * Created by david-liu on 2017/05/20 15:03.
 */
@Entity
@Table(name = "pro_promotion_category_account_stair_cut")
public class CategoryAccountStairCutPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = 5851625539986623084L;

    /**
     * 分类阶梯满减规则
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String promotionRules;

    @ManyToMany
    @JoinTable(name = "pro_promotion_category_account_stair_cut_category",
            joinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<Category> categories;

    public String getPromotionRules() {
        return promotionRules;
    }

    public List<ReachCutPromotionVo> getPromotionRuleVOList() {
        if (StringUtils.isBlank(this.promotionRules)) {
            return Lists.newArrayList();
        }
        return JSON.parseArray(this.promotionRules, ReachCutPromotionVo.class);
    }

    public void setPromotionRules(String promotionRules) {
        this.promotionRules = promotionRules;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
