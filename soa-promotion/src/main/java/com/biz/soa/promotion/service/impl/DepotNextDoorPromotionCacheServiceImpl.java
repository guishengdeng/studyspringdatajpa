package com.biz.soa.promotion.service.impl;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.SinglePurchaseGiftPromotion;
import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.SinglePurchaseGiftPromotionProduct;
import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.*;
import com.biz.gbck.dao.mysql.repository.promotion.*;
import com.biz.gbck.enums.product.promotion.CutPromotionTypeEnum;
import com.biz.gbck.enums.product.promotion.PromotionTypeEnum;
import com.biz.gbck.vo.product.promotion.PromotionNoticeVO;
import com.biz.gbck.vo.product.promotion.ReachCutPromotionVo;
import com.biz.soa.promotion.service.DepotNextDoorPromotionCacheService;
import com.biz.soa.promotion.transform.SinglePurchaseGiftPromotionProduct2SinglePurchaseGiftPromotionVO;
import com.biz.soa.promotion.vo.CategoryAccountCutPromotionVO;
import com.biz.soa.promotion.vo.CombinationAccountPromotionVO;
import com.biz.soa.promotion.vo.SinglePurchaseGiftPromotionVO;
import com.biz.soa.promotion.vo.WholeOrderAccountCutPromotionVO;
import com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import org.codelogger.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 隔壁仓库缓存ServiceImpl
 *
 * Created by david-liu on 2017/05/22 17:33.
 */
@Service
public class DepotNextDoorPromotionCacheServiceImpl implements DepotNextDoorPromotionCacheService {

    @Autowired
    private SinglePurchaseGiftPromotionRepository singlePurchaseGiftPromotionRepository;

    @Autowired
    private CategoryAccountReachCutPromotionRepository categoryAccountReachCutPromotionRepository;

    @Autowired
    private CategoryAccountStairCutPromotionRepository categoryAccountStairCutPromotionRepository;

    @Autowired
    private CombinationReachCutPromotionRepository combinationReachCutPromotionRepository;

    @Autowired
    private CombinationStairCutPromotionRepository combinationStairCutPromotionRepository;

    @Autowired
    private WholeOrderReachCutPromotionRepository wholeOrderReachCutPromotionRepository;

    @Autowired
    private WholeOrderStairCutPromotionRepository wholeOrderStairCutPromotionRepository;

    @PostConstruct
    public void initCache() {
        // 促销通知VO中, 促销类型为空, 更新所有的促销类型的信息到内存
        this.loadAllPromotionCache();
    }

    @Override
    public void loadCacheFromDb(PromotionNoticeVO noticeVO) {
        if (Objects.isNull(noticeVO.getPromotionType())) {
            // 促销通知VO中, 促销类型为空, 更新所有的促销类型的信息到内存
            this.loadAllPromotionCache();
        } else {
            // 促销通知VO中, 促销类型不为空, 更新指定的促销类型的信息到内存
            PromotionTypeEnum promotionType = noticeVO.getPromotionType();
            if (promotionType == PromotionTypeEnum.SINGLE_PURCHASE_GIFT) {
                singlePurchaseGiftPromotions.clear();
                this.loadSinglePurchaseGift();
            } else if (promotionType == PromotionTypeEnum.CATEGORY_ACCOUNT_CUT) {
                categoryAccountCutPromotions.clear();
                this.loadCategoryAccountReachCut();
                this.loadCategoryAccountStairCut();
            } else if (promotionType == PromotionTypeEnum.COMBINATION_ACCOUNT_CUT) {
                combinationAccountCutPromotions.clear();
                this.loadCombinationAcocuntReachCut();
                this.loadCombinationAcocuntStairCut();
            } else if (promotionType == PromotionTypeEnum.WHOLE_ORDER_ACCOUNT_CUT) {
                wholeOrderAccountCutPromotions.clear();
                this.loadWholeOrderAccountReachCut();
                this.loadWholeOrderAccountStairCut();
            }
        }
    }

    private void loadSinglePurchaseGift() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<SinglePurchaseGiftPromotion> singlePurchaseGiftPromotionList = singlePurchaseGiftPromotionRepository.findByStartDateBeforeAndEndDateAfter(now, now);
        if (Objects.nonNull(singlePurchaseGiftPromotionList) && CollectionUtils.isNotEmpty(singlePurchaseGiftPromotionList)) {
            for (SinglePurchaseGiftPromotion po : singlePurchaseGiftPromotionList) {
                List<SinglePurchaseGiftPromotionProduct> promotionProductList = po.getSinglePurchaseGiftPromotionProductList();
                List<SinglePurchaseGiftPromotionVO> promotionVOS = Lists.transform(promotionProductList, new SinglePurchaseGiftPromotionProduct2SinglePurchaseGiftPromotionVO());
                promotionVOS.forEach(promotionVO -> {
                    List<Long> orientedProductIds = promotionVO.getOrientedProducts();
                    orientedProductIds.forEach(orientedProductId -> {
                        List<SinglePurchaseGiftPromotionVO> productPromotions = singlePurchaseGiftPromotions.get(orientedProductId);
                        if (Objects.isNull(productPromotions)) {
                            productPromotions = Lists.newArrayList();
                        }
                        productPromotions.add(promotionVO);
                        singlePurchaseGiftPromotions.put(orientedProductId, productPromotions);
                    });
                });
            }
        }
    }

    private void loadCategoryAccountReachCut() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<CategoryAccountReachCutPromotion> categoryAccountReachCutPromotionList = categoryAccountReachCutPromotionRepository.findByStartDateBeforeAndEndDateAfter(now, now);
        if (Objects.nonNull(categoryAccountReachCutPromotionList)) {
            for (CategoryAccountReachCutPromotion po : categoryAccountReachCutPromotionList) {
                List<Category> categories = po.getCategories();
                if (CollectionUtils.isNotEmpty(categories)) {
                    List<ReachCutPromotionVo> promotionRules = po.getPromotionVOList();
                    CategoryAccountCutPromotionVO promotionVO = new CategoryAccountCutPromotionVO(CutPromotionTypeEnum.REACH_CUT, categories, promotionRules);
                    promotionVO.setCompanyId(po.getCompanyId());
                    promotionVO.setEffectEndTime(po.getEffectEndTime());
                    promotionVO.setEffectStartTime(po.getEffectStartTime());
                    promotionVO.setExcludeCategories(po.getExcludeCategories());
                    promotionVO.setExcludeCompany(po.getExcludeCompany());
                    promotionVO.setExclusive(po.getExclusive());
                    promotionVO.setExcludePriceGroup(po.getExcludePriceGroup());
                    promotionVO.setExcludeProducts(po.getExcludeProducts());
                    promotionVO.setExecuteDepartment(po.getExecuteDepartment());
                    promotionVO.setName(po.getName());
                    promotionVO.setAllowVoucher(po.getAllowVoucher());
                    promotionVO.setDescription(po.getDescription());
                    promotionVO.setEndDate(po.getEndDate());
                    promotionVO.setStartDate(po.getStartDate());
                    promotionVO.setOrientedCategories(po.getOrientedCategories());
                    promotionVO.setOrientedCompany(po.getOrientedCompany());
                    promotionVO.setOrientedPriceGroup(po.getOrientedPriceGroup());
                    promotionVO.setOrientedProducts(po.getOrientedProducts());
                    promotionVO.setOrientType(po.getOrientType());
                    promotionVO.setRoundCycle(po.getRoundCycle());
                    promotionVO.setPromotionRound(po.getPromotionRound());
                    categories.forEach(category -> this.dealWithCategoryPromotion(category, promotionVO));
                }
            }
        }
    }

    private void loadCategoryAccountStairCut() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<CategoryAccountStairCutPromotion> categoryAccountStairCutPromotionList = categoryAccountStairCutPromotionRepository.findByStartDateBeforeAndEndDateAfter(now, now);
        if (Objects.nonNull(categoryAccountStairCutPromotionList)) {
            for (CategoryAccountStairCutPromotion po : categoryAccountStairCutPromotionList) {
                List<Category> categories = po.getCategories();
                if (CollectionUtils.isNotEmpty(categories)) {
                    List<ReachCutPromotionVo> promotionRules = po.getPromotionRuleVOList();
                    CategoryAccountCutPromotionVO promotionVO = new CategoryAccountCutPromotionVO(CutPromotionTypeEnum.STAIR_CUT, categories, promotionRules);
                    promotionVO.setCompanyId(po.getCompanyId());
                    promotionVO.setEffectEndTime(po.getEffectEndTime());
                    promotionVO.setEffectStartTime(po.getEffectStartTime());
                    promotionVO.setExcludeCategories(po.getExcludeCategories());
                    promotionVO.setExcludeCompany(po.getExcludeCompany());
                    promotionVO.setExclusive(po.getExclusive());
                    promotionVO.setExcludePriceGroup(po.getExcludePriceGroup());
                    promotionVO.setExcludeProducts(po.getExcludeProducts());
                    promotionVO.setExecuteDepartment(po.getExecuteDepartment());
                    promotionVO.setName(po.getName());
                    promotionVO.setAllowVoucher(po.getAllowVoucher());
                    promotionVO.setDescription(po.getDescription());
                    promotionVO.setEndDate(po.getEndDate());
                    promotionVO.setStartDate(po.getStartDate());
                    promotionVO.setOrientedCategories(po.getOrientedCategories());
                    promotionVO.setOrientedCompany(po.getOrientedCompany());
                    promotionVO.setOrientedPriceGroup(po.getOrientedPriceGroup());
                    promotionVO.setOrientedProducts(po.getOrientedProducts());
                    promotionVO.setOrientType(po.getOrientType());
                    promotionVO.setRoundCycle(po.getRoundCycle());
                    promotionVO.setPromotionRound(po.getPromotionRound());
                    categories.forEach(category -> this.dealWithCategoryPromotion(category, promotionVO));
                }
            }
        }
    }

    private void loadCombinationAcocuntReachCut() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<CombinationReachCutPromotion> combinationReachCutPromotionList = combinationReachCutPromotionRepository.findByStartDateBeforeAndEndDateAfter(now, now);
        if (Objects.nonNull(combinationReachCutPromotionList)) {
            for (CombinationReachCutPromotion po : combinationReachCutPromotionList) {
                List<Product> productList = po.getProducts();
                List<ReachCutPromotionVo> promotionRules = po.getPromotionRuleVOList();
                CombinationAccountPromotionVO promotionVO = new CombinationAccountPromotionVO(CutPromotionTypeEnum.REACH_CUT, productList, promotionRules);
                promotionVO.setCompanyId(po.getCompanyId());
                promotionVO.setEffectEndTime(po.getEffectEndTime());
                promotionVO.setEffectStartTime(po.getEffectStartTime());
                promotionVO.setExcludeCategories(po.getExcludeCategories());
                promotionVO.setExcludeCompany(po.getExcludeCompany());
                promotionVO.setExclusive(po.getExclusive());
                promotionVO.setExcludePriceGroup(po.getExcludePriceGroup());
                promotionVO.setExcludeProducts(po.getExcludeProducts());
                promotionVO.setExecuteDepartment(po.getExecuteDepartment());
                promotionVO.setName(po.getName());
                promotionVO.setAllowVoucher(po.getAllowVoucher());
                promotionVO.setDescription(po.getDescription());
                promotionVO.setEndDate(po.getEndDate());
                promotionVO.setStartDate(po.getStartDate());
                promotionVO.setOrientedCategories(po.getOrientedCategories());
                promotionVO.setOrientedCompany(po.getOrientedCompany());
                promotionVO.setOrientedPriceGroup(po.getOrientedPriceGroup());
                promotionVO.setOrientedProducts(po.getOrientedProducts());
                promotionVO.setOrientType(po.getOrientType());
                promotionVO.setRoundCycle(po.getRoundCycle());
                promotionVO.setPromotionRound(po.getPromotionRound());
                productList.forEach(product -> dealWithCombinationPromotion(product, promotionVO));
            }
        }
    }

    private void loadCombinationAcocuntStairCut() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<CombinationStairCutPromotion> combinationStairCutPromotionList = combinationStairCutPromotionRepository.findByStartDateBeforeAndEndDateAfter(now, now);
        if (CollectionUtils.isNotEmpty(combinationStairCutPromotionList)) {
            for (CombinationStairCutPromotion po : combinationStairCutPromotionList) {
                List<Product> productList = po.getProducts();
                List<ReachCutPromotionVo> promotionRules = po.getPromotionRuleVOList();
                CombinationAccountPromotionVO promotionVO = new CombinationAccountPromotionVO(CutPromotionTypeEnum.STAIR_CUT, productList, promotionRules);
                promotionVO.setCompanyId(po.getCompanyId());
                promotionVO.setEffectEndTime(po.getEffectEndTime());
                promotionVO.setEffectStartTime(po.getEffectStartTime());
                promotionVO.setExcludeCategories(po.getExcludeCategories());
                promotionVO.setExcludeCompany(po.getExcludeCompany());
                promotionVO.setExclusive(po.getExclusive());
                promotionVO.setExcludePriceGroup(po.getExcludePriceGroup());
                promotionVO.setExcludeProducts(po.getExcludeProducts());
                promotionVO.setExecuteDepartment(po.getExecuteDepartment());
                promotionVO.setName(po.getName());
                promotionVO.setAllowVoucher(po.getAllowVoucher());
                promotionVO.setDescription(po.getDescription());
                promotionVO.setEndDate(po.getEndDate());
                promotionVO.setStartDate(po.getStartDate());
                promotionVO.setOrientedCategories(po.getOrientedCategories());
                promotionVO.setOrientedCompany(po.getOrientedCompany());
                promotionVO.setOrientedPriceGroup(po.getOrientedPriceGroup());
                promotionVO.setOrientedProducts(po.getOrientedProducts());
                promotionVO.setOrientType(po.getOrientType());
                promotionVO.setRoundCycle(po.getRoundCycle());
                promotionVO.setPromotionRound(po.getPromotionRound());
                productList.forEach(product -> dealWithCombinationPromotion(product, promotionVO));
            }
        }

    }

    private void loadWholeOrderAccountReachCut() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<WholeOrderReachCutPromotion> wholeOrderReachCutPromotionList = wholeOrderReachCutPromotionRepository.findByStartDateBeforeAndEndDateAfter(now, now);
        if (CollectionUtils.isNotEmpty(wholeOrderReachCutPromotionList)) {
            for (WholeOrderReachCutPromotion po : wholeOrderReachCutPromotionList) {
                List<ReachCutPromotionVo> promotionRuleVOList = po.getPromotionRuleVOList();
                WholeOrderAccountCutPromotionVO promotionVO = new WholeOrderAccountCutPromotionVO(CutPromotionTypeEnum.REACH_CUT, promotionRuleVOList);
                promotionVO.setCompanyId(po.getCompanyId());
                promotionVO.setEffectEndTime(po.getEffectEndTime());
                promotionVO.setEffectStartTime(po.getEffectStartTime());
                promotionVO.setExcludeCategories(po.getExcludeCategories());
                promotionVO.setExcludeCompany(po.getExcludeCompany());
                promotionVO.setExclusive(po.getExclusive());
                promotionVO.setExcludePriceGroup(po.getExcludePriceGroup());
                promotionVO.setExcludeProducts(po.getExcludeProducts());
                promotionVO.setExecuteDepartment(po.getExecuteDepartment());
                promotionVO.setName(po.getName());
                promotionVO.setAllowVoucher(po.getAllowVoucher());
                promotionVO.setDescription(po.getDescription());
                promotionVO.setEndDate(po.getEndDate());
                promotionVO.setStartDate(po.getStartDate());
                promotionVO.setOrientedCategories(po.getOrientedCategories());
                promotionVO.setOrientedCompany(po.getOrientedCompany());
                promotionVO.setOrientedPriceGroup(po.getOrientedPriceGroup());
                promotionVO.setOrientedProducts(po.getOrientedProducts());
                promotionVO.setOrientType(po.getOrientType());
                promotionVO.setRoundCycle(po.getRoundCycle());
                promotionVO.setPromotionRound(po.getPromotionRound());
                List<Long> orientedPriceGroupIds = po.getOrientedPriceGroup();
                if (CollectionUtils.isNotEmpty(orientedPriceGroupIds)) {
                    orientedPriceGroupIds.forEach(priceGroupId -> dealWithWholeOrderPromotion(priceGroupId, promotionVO));
                }
            }
        }
    }

    private void loadWholeOrderAccountStairCut() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<WholeOrderStairCutPromotion> wholeOrderReachCutPromotionList = wholeOrderStairCutPromotionRepository.findByStartDateBeforeAndEndDateAfter(now, now);
        if (CollectionUtils.isNotEmpty(wholeOrderReachCutPromotionList)) {
            for (WholeOrderStairCutPromotion po : wholeOrderReachCutPromotionList) {
                List<ReachCutPromotionVo> promotionRuleVOList = po.getPromotionRuleVOList();
                WholeOrderAccountCutPromotionVO promotionVO = new WholeOrderAccountCutPromotionVO(CutPromotionTypeEnum.STAIR_CUT, promotionRuleVOList);
                promotionVO.setCompanyId(po.getCompanyId());
                promotionVO.setEffectEndTime(po.getEffectEndTime());
                promotionVO.setEffectStartTime(po.getEffectStartTime());
                promotionVO.setExcludeCategories(po.getExcludeCategories());
                promotionVO.setExcludeCompany(po.getExcludeCompany());
                promotionVO.setExclusive(po.getExclusive());
                promotionVO.setExcludePriceGroup(po.getExcludePriceGroup());
                promotionVO.setExcludeProducts(po.getExcludeProducts());
                promotionVO.setExecuteDepartment(po.getExecuteDepartment());
                promotionVO.setName(po.getName());
                promotionVO.setAllowVoucher(po.getAllowVoucher());
                promotionVO.setDescription(po.getDescription());
                promotionVO.setEndDate(po.getEndDate());
                promotionVO.setStartDate(po.getStartDate());
                promotionVO.setOrientedCategories(po.getOrientedCategories());
                promotionVO.setOrientedCompany(po.getOrientedCompany());
                promotionVO.setOrientedPriceGroup(po.getOrientedPriceGroup());
                promotionVO.setOrientedProducts(po.getOrientedProducts());
                promotionVO.setOrientType(po.getOrientType());
                promotionVO.setRoundCycle(po.getRoundCycle());
                promotionVO.setPromotionRound(po.getPromotionRound());
                List<Long> orientedPriceGroupIds = po.getOrientedPriceGroup();
                if (CollectionUtils.isNotEmpty(orientedPriceGroupIds)) {
                    orientedPriceGroupIds.forEach(priceGroupId -> dealWithWholeOrderPromotion(priceGroupId, promotionVO));
                }
            }
        }
    }

    private void dealWithCategoryPromotion(Category category, CategoryAccountCutPromotionVO promotionVO) {
        Long categoryId = category.getId();
        List<CategoryAccountCutPromotionVO> categoryPromotions = categoryAccountCutPromotions.get(categoryId);
        if (Objects.isNull(categoryPromotions)) {
            categoryPromotions = Lists.newArrayList();
        }
        categoryPromotions.add(promotionVO);
        categoryAccountCutPromotions.put(categoryId, categoryPromotions);
    }

    private void dealWithCombinationPromotion(Product product, CombinationAccountPromotionVO promotionVO) {
        Long productId = product.getId();
        List<CombinationAccountPromotionVO> promotionVOS = combinationAccountCutPromotions.get(productId);
        if (CollectionUtils.isEmpty(promotionVOS)) {
            promotionVOS = Lists.newArrayList();
        }
        promotionVOS.add(promotionVO);
        combinationAccountCutPromotions.put(productId, promotionVOS);
    }

    private void dealWithWholeOrderPromotion(Long priceGroupId, WholeOrderAccountCutPromotionVO promotionVO) {
        List<WholeOrderAccountCutPromotionVO> promotionVOS = wholeOrderAccountCutPromotions.get(priceGroupId);
        if (Objects.isNull(promotionVOS)) {
            promotionVOS = Lists.newArrayList();
        }
        promotionVOS.add(promotionVO);
        wholeOrderAccountCutPromotions.put(priceGroupId, promotionVOS);
    }

    private void loadAllPromotionCache() {
        // 促销通知VO中, 促销类型为空, 更新所有的促销类型的信息到内存
        singlePurchaseGiftPromotions.clear();
        this.loadSinglePurchaseGift();
        categoryAccountCutPromotions.clear();
        this.loadCategoryAccountReachCut();
        this.loadCategoryAccountStairCut();
        combinationAccountCutPromotions.clear();
        this.loadCombinationAcocuntReachCut();
        this.loadCombinationAcocuntStairCut();
        wholeOrderAccountCutPromotions.clear();
        this.loadWholeOrderAccountReachCut();
        this.loadWholeOrderAccountStairCut();
    }
}
