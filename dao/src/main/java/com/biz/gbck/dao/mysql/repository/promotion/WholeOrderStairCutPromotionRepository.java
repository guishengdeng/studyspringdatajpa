package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.WholeOrderStairCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepository;

/**
 * 整单阶梯满减促销Repository
 *
 * Created by david-liu on 2017/05/20 17:41.
 */
public interface WholeOrderStairCutPromotionRepository extends CommonJpaRepository<WholeOrderStairCutPromotion, Long>, WholeOrderStairCutPromotionDao {
}
