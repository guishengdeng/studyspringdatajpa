package com.biz.gbck.dao.mysql.po.product.promotion.combination;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import com.biz.gbck.vo.product.promotion.QuantityCombinationProductVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Table;
import org.codelogger.utils.StringUtils;

/**
 * 数量组合促销
 * <p>
 * 设置组合总价和数量，在组合范围内的任一选择商品数量达到组合总数量即可参加数量组合促销。
 * 例如：数量组合促销为总数量2总价200，商品范围A、B、C单价分别为130、140、150，任一选择ABC中的数量达到2即可参与（其中商品可以重复）。
 * </p>
 *
 * Created by david-liu on 2017/04/24 10:52.
 */
@Table(name = "pro_promotion_quantity_combination")
public class QuantityCombinationPromotion extends ProductPromotion {
    private static final long serialVersionUID = 7199862943657058813L;

    /**
     * 组合规则
     */
    @Column(columnDefinition = "TEXT")
    private String quantityCombinationPromotionRules;

    /**
     * 组合价
     */
    @Column
    private Integer combinationPrice;

    /**
     * 组合数量
     */
    @Column
    private Integer combinationQuantity;

    public String getQuantityCombinationPromotionRules() {
        return quantityCombinationPromotionRules;
    }

    public void setQuantityCombinationPromotionRules(String quantityCombinationPromotionRules) {
        this.quantityCombinationPromotionRules = quantityCombinationPromotionRules;
    }

    public Integer getCombinationPrice() {
        return combinationPrice;
    }

    public void setCombinationPrice(Integer combinationPrice) {
        this.combinationPrice = combinationPrice;
    }

    public Integer getCombinationQuantity() {
        return combinationQuantity;
    }

    public void setCombinationQuantity(Integer combinationQuantity) {
        this.combinationQuantity = combinationQuantity;
    }

    public List<QuantityCombinationProductVo> getQuantityCombinationRules() {
        if (StringUtils.isBlank(this.quantityCombinationPromotionRules)) {
            return Lists.newArrayList();
        }

        return JSON.parseArray(this.quantityCombinationPromotionRules, QuantityCombinationProductVo.class);
    }
}
