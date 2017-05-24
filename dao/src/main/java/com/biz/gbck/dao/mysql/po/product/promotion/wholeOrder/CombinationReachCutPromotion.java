package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.vo.product.promotion.ReachCutPromotionVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.*;
import org.codelogger.utils.StringUtils;

/**
 * 组合倍增满减
 *
 * Created by david-liu on 2017/05/20 15:18.
 */
@Entity
@Table(name = "pro_promotion_combination_reach_cut")
public class CombinationReachCutPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = -8981462922061176710L;

    /**
     * 组合满减规则, 出入ReachCutPromotionVO序列化之后的JSON字符串
     */
    @Column(columnDefinition = "TEXT")
    private String promotionRules;

    @ManyToMany
    @JoinTable(name = "pro_promotion_combination_reach_cut_product",
            joinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
