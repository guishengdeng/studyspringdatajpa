package com.biz.gbck.dao.mysql.repository.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.SinglePurchaseGiftPromotion;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.sql.Timestamp;
import java.util.List;

/**
 * 单品买赠促销Repository
 *
 * Created by david-liu on 2017/05/20 17:05.
 */
public interface SinglePurchaseGiftPromotionRepository extends CommonJpaRepository<SinglePurchaseGiftPromotion, Long>, SinglePurchaseGiftPromotionDao {
    // 查找当前生效的单品买赠促销
    List<SinglePurchaseGiftPromotion> findByStartDateBeforeAndEndDateAfter(Timestamp now1, Timestamp now2);
}
