package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.WholeOrderReachCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 整单倍增满减促销RepositoryImpl
 *
 * Created by david-liu on 2017/05/20 17:29.
 */
public class WholeOrderReachCutPromotionRepositoryImpl extends CommonJpaRepositoryBean<WholeOrderReachCutPromotion, Long> implements WholeOrderReachCutPromotionDao {
    @Autowired
    public WholeOrderReachCutPromotionRepositoryImpl(EntityManager em) {
        super(WholeOrderReachCutPromotion.class, em);
    }
}
