package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.CategoryAccountReachCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.sql.Timestamp;
import java.util.List;

/**
 * 分类满减Repository
 *
 * Created by david-liu on 2017/05/20 17:14.
 */
public interface CategoryAccountReachCutPromotionRepository extends CommonJpaRepository<CategoryAccountReachCutPromotion, Long>, CategoryAccountReachCutPromotionDao {

    List<CategoryAccountReachCutPromotion> findByStartDateBeforeAndEndDateAfter(Timestamp time1, Timestamp time2);

}
