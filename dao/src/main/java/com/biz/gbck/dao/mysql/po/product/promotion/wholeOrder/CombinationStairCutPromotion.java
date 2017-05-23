package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import java.util.List;
import javax.persistence.*;

/**
 * 组合倍增满减
 *
 * Created by david-liu on 2017/05/20 15:19.
 */
@Entity
@Table(name = "pro_promotion_combination_stair_cut")
public class CombinationStairCutPromotion extends WholeOrderPromotion {
    private static final long serialVersionUID = 6926831660060915982L;

    /**
     * 组合倍增满减规则
     */
    @Column(columnDefinition = "TEXT")
    private String promotionRules;

    @ManyToMany
    @JoinTable(name = "pro_promotion_combination_stair_cut_product",
            joinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    public String getPromotionRules() {
        return promotionRules;
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
