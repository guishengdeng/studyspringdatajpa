package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.vo.product.promotion.ReachCutPromotionVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.*;
import org.codelogger.utils.StringUtils;

/**
 * 阶梯满减
 * <p>
 * 商品每满X元减Y元，例如：A商品每满100元立减10元，A商品每满200元立减30，依次类推。
 * </p>
 *
 * Created by david-liu on 2017/04/24 09:08.
 */
@Entity
@Table(name = "pro_promotion_stair_cut")
public class StairCutPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = -7627274382954334393L;

    /**
     * 促销明细
     */
    @Column(columnDefinition = "TEXT")
    private String stairCutPromotionRules;

    /**
     * 可参与促销的指定商品
     */
    @ManyToMany
    @JoinTable(name = "pro_promotion_stair_cut_product",
            joinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getStairCutPromotionRules() {
        return stairCutPromotionRules;
    }

    public void setStairCutPromotionRules(String stairCutPromotionRules) {
        this.stairCutPromotionRules = stairCutPromotionRules;
    }

    public List<ReachCutPromotionVo> getCutPromotionRules() {
        if (StringUtils.isBlank(this.stairCutPromotionRules)) {
            return Lists.newArrayList();
        }

        return JSON.parseArray(this.stairCutPromotionRules, ReachCutPromotionVo.class);
    }
}
