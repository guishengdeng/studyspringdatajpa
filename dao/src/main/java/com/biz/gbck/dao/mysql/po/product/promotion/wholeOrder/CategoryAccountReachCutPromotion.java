package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.vo.product.promotion.ReachCutPromotionVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.lang3.StringUtils;

/**
 * 分类倍增满减(每满x减y)
 * Created by david-liu on 2017/05/19 17:04.
 */
@Entity
@Table(name = "pro_promotion_category_account_reach_cut")
public class CategoryAccountReachCutPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = 3538280383137561378L;

    /**
     * 分类促销满减规则, 只会存在一条ReachCutPromotionVO的JSON
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String promotionRules;

    /**
     * 可参与促销的指定商品
     */
    @ManyToMany
    @JoinTable(name = "pro_promotion_category_account_reach_cut_category",
            joinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<Category> categories;

    public List<ReachCutPromotionVo> getPromotionVOList() {
        if (StringUtils.isBlank(this.promotionRules)) {
            return Lists.newArrayList();
        }
        return JSON.parseArray(promotionRules, ReachCutPromotionVo.class);
    }

    public String getPromotionRules() {
        return this.promotionRules;
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
