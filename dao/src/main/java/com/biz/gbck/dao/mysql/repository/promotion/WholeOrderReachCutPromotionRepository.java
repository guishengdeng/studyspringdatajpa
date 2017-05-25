package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.WholeOrderReachCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.sql.Timestamp;
import java.util.List;

/**
 * 整单倍增满减促销Repository
 *
 * Created by david-liu on 2017/05/20 17:25.
 */
public interface WholeOrderReachCutPromotionRepository extends CommonJpaRepository<WholeOrderReachCutPromotion, Long>, WholeOrderReachCutPromotionDao {

    List<WholeOrderReachCutPromotion> findByStartDateBeforeAndEndDateAfter(Timestamp t1, Timestamp t2);

}
