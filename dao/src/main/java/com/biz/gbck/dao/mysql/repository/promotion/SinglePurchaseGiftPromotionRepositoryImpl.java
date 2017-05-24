package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.SinglePurchaseGiftPromotion;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 单品买赠促销RepositoryImpl
 *
 * Created by david-liu on 2017/05/20 17:06.
 */
public class SinglePurchaseGiftPromotionRepositoryImpl extends CommonJpaRepositoryBean<SinglePurchaseGiftPromotion, Long> implements SinglePurchaseGiftPromotionDao {
    @Autowired
    public SinglePurchaseGiftPromotionRepositoryImpl(EntityManager em) {
        super(SinglePurchaseGiftPromotion.class, em);
    }
}
