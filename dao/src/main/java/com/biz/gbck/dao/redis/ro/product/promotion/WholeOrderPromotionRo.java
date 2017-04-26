package com.biz.gbck.dao.redis.ro.product.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.AccountPerUnitCutPromotion;
import com.biz.gbck.dao.mysql.po.product.promotion.wholeOrder.MultipleIncrementGiftPromotion;
import com.biz.gbck.enums.product.promotion.WholeOrderPromotionTypeEnum;
import com.biz.gbck.vo.product.promotion.FullRedemptionPromotionVo;
import com.biz.gbck.vo.product.promotion.StairGiftPromotionRuleVo;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 整单促销Ro
 * <p>
 * 在保存整单促销Ro的时候, 将整单Ro促销的规则细化
 * </p>
 *
 * Created by david-liu on 2017/04/25 15:12.
 */
@Ro(key = "pro:promotion:WholeOrderPromotionRo")
@RoSortedSet(key = "list")
public class WholeOrderPromotionRo extends BaseRedisObject<String> {
    private static final long serialVersionUID = -758114918424985970L;

    /**
     * 整单促销类型
     *
     * @see WholeOrderPromotionTypeEnum#value
     */
    private Integer wholeOrderPromotionType;

    /**
     * 能参与促销的商品的编码(存放方式是商品编码按照逗号分割)
     */
    private String productCodes;

    /**
     * 每满多少钱
     *
     * @see AccountPerUnitCutPromotion#perUnitAccount
     */
    private Integer perUnitAccount;

    /**
     * 减多少钱
     *
     * @see AccountPerUnitCutPromotion#cutAccount
     */
    private Integer cutAccount;

    /**
     * 赠品满足金额(每满x元)
     *
     * @see MultipleIncrementGiftPromotion#accountLimit
     * @see FullRedemptionPromotionVo#accountLimit
     */
    private Integer accountLimit;

    /**
     * 赠品商品编码
     *
     * @see MultipleIncrementGiftPromotion#product
     * @see FullRedemptionPromotionVo#giftProductCode
     */
    private String giftProductCode;

    /**
     * 赠品数量
     *
     * @see StairGiftPromotionRuleVo#giftQuantity
     * @see FullRedemptionPromotionVo#giftQuantity
     */
    private Integer giftQuantity;

    /**
     * 赠品数量上限
     *
     * @see StairGiftPromotionRuleVo#totalGiftQuantityLimit
     */
    private Integer totalGiftQuantityLimit;

    /**
     * 换购价
     *
     * @see FullRedemptionPromotionVo#redemptionPrice
     */
    private Integer redemptionPrice;


    /**
     * 是否互斥
     */
    private Boolean isExclusive;

    public Integer getWholeOrderPromotionType() {
        return wholeOrderPromotionType;
    }

    public void setWholeOrderPromotionType(Integer wholeOrderPromotionType) {
        this.wholeOrderPromotionType = wholeOrderPromotionType;
    }

    public String getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(String productCodes) {
        this.productCodes = productCodes;
    }

    public Integer getPerUnitAccount() {
        return perUnitAccount;
    }

    public void setPerUnitAccount(Integer perUnitAccount) {
        this.perUnitAccount = perUnitAccount;
    }

    public Integer getCutAccount() {
        return cutAccount;
    }

    public void setCutAccount(Integer cutAccount) {
        this.cutAccount = cutAccount;
    }

    public Integer getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(Integer accountLimit) {
        this.accountLimit = accountLimit;
    }

    public String getGiftProductCode() {
        return giftProductCode;
    }

    public void setGiftProductCode(String giftProductCode) {
        this.giftProductCode = giftProductCode;
    }

    public Integer getGiftQuantity() {
        return giftQuantity;
    }

    public void setGiftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
    }

    public Integer getTotalGiftQuantityLimit() {
        return totalGiftQuantityLimit;
    }

    public void setTotalGiftQuantityLimit(Integer totalGiftQuantityLimit) {
        this.totalGiftQuantityLimit = totalGiftQuantityLimit;
    }

    public Integer getRedemptionPrice() {
        return redemptionPrice;
    }

    public void setRedemptionPrice(Integer redemptionPrice) {
        this.redemptionPrice = redemptionPrice;
    }

    public Boolean getExclusive() {
        return isExclusive;
    }

    public void setExclusive(Boolean exclusive) {
        isExclusive = exclusive;
    }
}
