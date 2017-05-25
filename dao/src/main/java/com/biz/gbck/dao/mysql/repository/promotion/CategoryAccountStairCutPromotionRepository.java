package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.CategoryAccountStairCutPromotion;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by david-liu on 2017/05/20 17:18.
 */
public interface CategoryAccountStairCutPromotionRepository extends CommonJpaRepository<CategoryAccountStairCutPromotion, Long>, CategoryAccountStairCutPromotionDao {

    List<CategoryAccountStairCutPromotion> findByStartDateBeforeAndEndDateAfter(Timestamp time1, Timestamp time2);

}
