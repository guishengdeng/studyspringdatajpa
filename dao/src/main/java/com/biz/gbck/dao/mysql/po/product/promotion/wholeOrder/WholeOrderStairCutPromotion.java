package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by david-liu on 2017/05/20 15:44.
 */
@Entity
@Table(name = "pro_promotion_whole_order_reach_cut")
public class WholeOrderStairCutPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = 2842080670020280795L;

    /**
     * 促销规则
     */
    @Column(columnDefinition = "TEXT")
    private String promotionRules;

    public String getPromotionRules() {
        return promotionRules;
    }

    public void setPromotionRules(String promotionRules) {
        this.promotionRules = promotionRules;
    }
}
