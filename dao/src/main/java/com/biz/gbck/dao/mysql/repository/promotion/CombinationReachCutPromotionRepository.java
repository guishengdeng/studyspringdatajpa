package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.CombinationReachCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.sql.Timestamp;
import java.util.List;

/**
 * 组合满减Repository
 *
 * Created by david-liu on 2017/05/20 17:20.
 */
public interface CombinationReachCutPromotionRepository extends CommonJpaRepository<CombinationReachCutPromotion, Long>, CombinationReachCutPromotionDao {

    List<CombinationReachCutPromotion> findByStartDateBeforeAndEndDateAfter(Timestamp t1, Timestamp t2);
}
