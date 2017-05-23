package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.CombinationStairCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 组合阶梯满减RepositoryImpl
 *
 * Created by david-liu on 2017/05/20 17:23.
 */
public class CombinationStairCutPromotionRepositoryImpl extends CommonJpaRepositoryBean<CombinationStairCutPromotion, Long> implements CombinationStairCutPromotionDao {
    @Autowired
    public CombinationStairCutPromotionRepositoryImpl(EntityManager em) {
        super(CombinationStairCutPromotion.class, em);
    }
}
