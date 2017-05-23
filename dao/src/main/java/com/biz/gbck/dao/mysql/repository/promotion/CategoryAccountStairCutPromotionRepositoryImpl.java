package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.CategoryAccountStairCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by david-liu on 2017/05/20 17:18.
 */
public class CategoryAccountStairCutPromotionRepositoryImpl extends CommonJpaRepositoryBean<CategoryAccountStairCutPromotion, Long> implements CategoryAccountStairCutPromotionDao {
    @Autowired
    public CategoryAccountStairCutPromotionRepositoryImpl(EntityManager em) {
        super(CategoryAccountStairCutPromotion.class, em);
    }
}
