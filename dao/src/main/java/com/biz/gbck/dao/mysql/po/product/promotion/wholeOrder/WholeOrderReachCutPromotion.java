package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.vo.product.promotion.ReachCutPromotionVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.codelogger.utils.StringUtils;

/**
 * Created by david-liu on 2017/05/20 15:43.
 */
@Entity
@Table(name = "pro_promotion_whole_order_reach_cut")
public class WholeOrderReachCutPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = 8661171952382526388L;

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

    public List<ReachCutPromotionVo> getPromotionRuleVOList() {
        if (StringUtils.isBlank(this.promotionRules)) {
            return Lists.newArrayList();
        }
        return JSON.parseArray(this.promotionRules, ReachCutPromotionVo.class);
    }
}
