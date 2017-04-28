package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.vo.product.promotion.FullRedemptionPromotionVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.*;
import org.codelogger.utils.StringUtils;

/**
 * 满额换购促销
 * <p>
 * 每满X元加Y元换购一款商品,例如：商品A、B、C售价20、40、60，满100元加10元换购A，满200加15换购B，满300元加18换后C，同一单只能选一种换购。
 * </p>
 *
 * Created by david-liu on 2017/04/24 10:18.
 */
@Entity
@Table(name = "pro_promotion_full_redemption")
public class FullRedemptionPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = 42746536900930252L;

    /**
     * 促销明细
     */
    @Column(columnDefinition = "TEXT")
    private String redemptionPromotionRules;

    /**
     * 可参与促销的指定商品
     */
    @ManyToMany
    @JoinTable(name = "pro_promotion_full_redemption_product",
            joinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    public String getRedemptionPromotionRules() {
        return redemptionPromotionRules;
    }

    public void setRedemptionPromotionRules(String redemptionPromotionRules) {
        this.redemptionPromotionRules = redemptionPromotionRules;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<FullRedemptionPromotionVo> getFullRedemptionPromotionRules() {
        if (StringUtils.isBlank(this.redemptionPromotionRules)) {
            return Lists.newArrayList();
        }

        return JSON.parseArray(this.redemptionPromotionRules, FullRedemptionPromotionVo.class);
    }
}
