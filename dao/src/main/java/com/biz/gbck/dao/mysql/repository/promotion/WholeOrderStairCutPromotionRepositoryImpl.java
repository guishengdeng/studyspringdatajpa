package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.WholeOrderStairCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by david-liu on 2017/05/20 17:59.
 */
public class WholeOrderStairCutPromotionRepositoryImpl extends CommonJpaRepositoryBean<WholeOrderStairCutPromotion, Long> implements WholeOrderStairCutPromotionDao {
    @Autowired
    public WholeOrderStairCutPromotionRepositoryImpl(EntityManager em) {
        super(WholeOrderStairCutPromotion.class, em);
    }
}
