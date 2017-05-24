package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.SimpleSpecialOfferPromotion;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 简单特价RepositoryImpl
 *
 * Created by david-liu on 2017/05/20 16:58.
 */
public class SimpleSpecialOfferPromotionRepositoryImpl extends CommonJpaRepositoryBean<SimpleSpecialOfferPromotion, Long> implements SimpleSpecialOfferPromotionDao {

    @Autowired
    public SimpleSpecialOfferPromotionRepositoryImpl(EntityManager em) {
        super(SimpleSpecialOfferPromotion.class, em);
    }
}
