package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.CategoryAccountReachCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 分类满减促销RepositoryImpl
 *
 * Created by david-liu on 2017/05/20 17:15.
 */
public class CategoryAccountReachCutPromotionRepositoryImpl extends CommonJpaRepositoryBean<CategoryAccountReachCutPromotion, Long> implements CategoryAccountReachCutPromotionDao {
    @Autowired
    public CategoryAccountReachCutPromotionRepositoryImpl(EntityManager em) {
        super(CategoryAccountReachCutPromotion.class, em);
    }
}
