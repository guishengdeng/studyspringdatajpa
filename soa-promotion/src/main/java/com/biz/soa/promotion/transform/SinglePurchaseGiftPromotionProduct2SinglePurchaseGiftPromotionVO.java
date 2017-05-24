package com.biz.soa.promotion.transform;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.SinglePurchaseGiftPromotion;
import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.SinglePurchaseGiftPromotionProduct;
import com.biz.soa.promotion.vo.SinglePurchaseGiftPromotionVO;
import com.google.common.base.Function;

/**
 * 转换器(单品买赠PO --> 单品买赠VO)
 *
 * Created by david-liu on 2017/05/23 09:51.
 */
public class SinglePurchaseGiftPromotionProduct2SinglePurchaseGiftPromotionVO implements Function<SinglePurchaseGiftPromotionProduct, SinglePurchaseGiftPromotionVO> {
    @Override
    public SinglePurchaseGiftPromotionVO apply(SinglePurchaseGiftPromotionProduct po) {
        SinglePurchaseGiftPromotionVO vo = new SinglePurchaseGiftPromotionVO();
        Product giftProduct = po.getProduct();
        SinglePurchaseGiftPromotion promotion = po.getSinglePurchaseGiftPromotion();
        vo.setGiftProductId(giftProduct.getId());
        vo.setStartDate(promotion.getStartDate());
        vo.setEndDate(promotion.getEndDate());
        vo.setGiftQuantity(po.getGiftQuantity());
        vo.setGiftQuantityLimit(po.getGiftQuantityLimit());
        vo.setLimitStock(po.getLimitStock());
        vo.setQuantityLimit(po.getQuantityLimit());
        vo.setAllowVoucher(promotion.getAllowVoucher());
        vo.setCompanyId(promotion.getCompanyId());
        vo.setEffectStartTime(promotion.getEffectStartTime());
        vo.setEffectEndTime(promotion.getEffectEndTime());
        vo.setExcludeCategories(promotion.getExcludeCategories());
        vo.setExcludeCompany(promotion.getExcludeCompany());
        vo.setExcludePriceGroup(promotion.getExcludePriceGroup());
        vo.setExcludeProducts(promotion.getExcludeProducts());
        vo.setExclusive(promotion.getExclusive());
        vo.setExecuteDepartment(promotion.getExecuteDepartment());
        vo.setName(promotion.getName());
        vo.setOrientedCategories(promotion.getOrientedCategories());
        vo.setOrientedCompany(promotion.getOrientedCompany());
        vo.setOrientedPriceGroup(promotion.getOrientedPriceGroup());
        vo.setOrientedProducts(promotion.getOrientedProducts());
        vo.setDescription(promotion.getDescription());
        vo.setRoundCycle(promotion.getRoundCycle());
        vo.setPromotionRound(promotion.getPromotionRound());
        return vo;
    }
}
