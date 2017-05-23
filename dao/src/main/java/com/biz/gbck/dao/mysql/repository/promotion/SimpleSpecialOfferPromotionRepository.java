package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.SimpleSpecialOfferPromotion;
import com.biz.support.jpa.repository.CommonJpaRepository;

/**
 * 简单特价Repository
 *
 * Created by david-liu on 2017/05/20 16:57.
 */
public interface SimpleSpecialOfferPromotionRepository extends CommonJpaRepository<SimpleSpecialOfferPromotion, Long>, SimpleSpecialOfferPromotionDao {
}
