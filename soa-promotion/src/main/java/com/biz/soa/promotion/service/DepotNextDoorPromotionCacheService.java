package com.biz.soa.promotion.service;

import com.biz.gbck.vo.product.promotion.PromotionNoticeVO;
import com.biz.soa.promotion.vo.CategoryAccountCutPromotionVO;
import com.biz.soa.promotion.vo.CombinationAccountPromotionVO;
import com.biz.soa.promotion.vo.SinglePurchaseGiftPromotionVO;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

/**
 * 隔壁仓库促销缓存Service
 * Created by david-liu on 2017/05/19 15:08.
 */
public interface DepotNextDoorPromotionCacheService {
    /**
     * Key为商品ID, Value为单品买赠促销VO集合
     */
    Map<Long, List<SinglePurchaseGiftPromotionVO>> singlePurchaseGiftPromotions = Maps.newConcurrentMap();

    /**
     * Key为分类ID, Value为分类满减促销VO集合
     */
    Map<Long, List<CategoryAccountCutPromotionVO>> categoryAccountCutPromotions = Maps.newConcurrentMap();

    /**
     * Key为商品ID, Value为组合满减促销VO集合
     */
    Map<Long, List<CombinationAccountPromotionVO>> combinationAccountCutPromotions = Maps.newConcurrentMap();

    void loadCacheFromDb(PromotionNoticeVO noticeVO);
}
