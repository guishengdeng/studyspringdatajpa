package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.WholeOrderReachCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepository;

/**
 * 整单倍增满减促销Repository
 *
 * Created by david-liu on 2017/05/20 17:25.
 */
public interface WholeOrderReachCutPromotionRepository extends CommonJpaRepository<WholeOrderReachCutPromotion, Long>, WholeOrderReachCutPromotionDao {
}
