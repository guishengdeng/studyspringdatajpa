package com.biz.gbck.dao.mysql.po.product.promotion.combination;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import com.biz.gbck.vo.product.promotion.CombinationPromotionProductVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.codelogger.utils.StringUtils;

/**
 * 组合促销
 * <p>
 * 几个商品组合设定各自的数量和价格，当订单满足组合促销的每个商品的数量时可参与对应的组合促销。
 * 例如：商品A、B、C销售价分别为100、200、150，组合促销规则2A、1B、1C销单价分别为75、150、100，组合总价为400，当购买2A1B1C时可以参与。
 * </p>
 *
 * Created by david-liu on 2017/04/24 10:44.
 */
@Entity
@Table(name = "pro_promotion_combination")
public class CombinationPromotion extends ProductPromotion {
    private static final long serialVersionUID = -2342186424409724749L;

    @Column(columnDefinition = "TEXT")
    private String combinationPromotionRules;

    public String getCombinationPromotionRules() {
        return combinationPromotionRules;
    }

    public void setCombinationPromotionRules(String combinationPromotionRules) {
        this.combinationPromotionRules = combinationPromotionRules;
    }

    public List<CombinationPromotionProductVo> getCombinationProductRules() {
        if (StringUtils.isBlank(this.combinationPromotionRules)) {
            return Lists.newArrayList();
        }

        return JSON.parseArray(this.combinationPromotionRules, CombinationPromotionProductVo.class);
    }
}
