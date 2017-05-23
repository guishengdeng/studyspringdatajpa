package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.CombinationReachCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 组合满减促销RepositoryImpl
 *
 * Created by david-liu on 2017/05/20 17:20.
 */
public class CombinationReachCutPromotionRepositoryImpl extends CommonJpaRepositoryBean<CombinationReachCutPromotion, Long> implements CombinationReachCutPromotionDao {
    @Autowired
    public CombinationReachCutPromotionRepositoryImpl(EntityManager em) {
        super(CombinationReachCutPromotion.class, em);
    }
}
