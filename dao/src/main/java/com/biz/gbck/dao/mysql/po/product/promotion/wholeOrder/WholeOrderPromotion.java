package com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.product.promotion.ProductPromotion;
import com.biz.gbck.enums.product.promotion.WholeOrderPromotionLimitEnum;
import javax.persistence.*;

/**
 * 整单促销
 *
 * Created by david-liu on 2017/04/24 11:50.
 */
@MappedSuperclass
public class WholeOrderPromotion extends ProductPromotion {
    private static final long serialVersionUID = 2145556237168912383L;

    /**
     * 促销的使用范围
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private WholeOrderPromotionLimitEnum productLimit;

    /**
     * 可参与促销的分类
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public WholeOrderPromotionLimitEnum getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(WholeOrderPromotionLimitEnum productLimit) {
        this.productLimit = productLimit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
