package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.vo.product.promotion.StairGiftPromotionRuleVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.lang3.StringUtils;

/**
 * 阶梯满赠
 * <p>
 * 每满X元赠Y瓶酒（赠酒只能是同一种酒），例如：A商品每满100元赠1瓶B，A商品每满200元赠3瓶B，依次类推。
 * </p>
 *
 * Created by david-liu on 2017/04/24 10:01.
 */
@Entity
@Table(name = "pro_promotion_stair_gift")
public class StairGiftPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = 8726285325465926326L;

    /**
     * 促销明细
     */
    @Column(columnDefinition = "TEXT")
    private String giftPromotionRules;

    /**
     * 可参与促销的指定商品
     */
    @ManyToMany
    @JoinTable(name = "pro_promotion_stair_gift_product",
            joinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    public String getGiftPromotionRules() {
        return giftPromotionRules;
    }

    public void setGiftPromotionRules(String giftPromotionRules) {
        this.giftPromotionRules = giftPromotionRules;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<StairGiftPromotionRuleVo> getStairPromotionRules() {
        if (StringUtils.isBlank(this.giftPromotionRules)) {
            return Lists.newArrayList();
        }

        return JSON.parseArray(this.giftPromotionRules, StairGiftPromotionRuleVo.class);
    }
}
