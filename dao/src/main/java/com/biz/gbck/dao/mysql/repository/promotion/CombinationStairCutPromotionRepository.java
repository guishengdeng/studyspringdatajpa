package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.CombinationStairCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.sql.Timestamp;
import java.util.List;

/**
 * 组合阶梯满减Repository
 *
 * Created by david-liu on 2017/05/20 17:22.
 */
public interface CombinationStairCutPromotionRepository extends CommonJpaRepository<CombinationStairCutPromotion, Long>, CombinationStairCutPromotionDao {

    List<CombinationStairCutPromotion> findByStartDateBeforeAndEndDateAfter(Timestamp t1, Timestamp t2);
}
