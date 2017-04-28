package com.biz.gbck.dao.redis.ro.product.promotion;

import com.biz.gbck.dao.mysql.po.product.promotion.combination.QuantityCombinationPromotion;
import com.biz.gbck.enums.product.promotion.CombinationPromotionTypeEnum;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 组合促销Ro
 * <p>
 * Ro的ID为商品编码 + 价格组ID
 * </p>
 *
 * Created by david-liu on 2017/04/26 09:06.
 */
@Ro(key = "pro:promotion:CombinationPromotionRo")
@RoSortedSet(key = "list")
public class CombinationPromotionRo extends BaseRedisObject<String> {
    private static final long serialVersionUID = 7474811331631694212L;

    /**
     * 组合促销的类型
     */
    private CombinationPromotionTypeEnum combinationPromotionType;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 价格组Id
     */
    private Long priceGroupId;

    /**
     * 参与组合促销的商品编码的集合
     */
    private String productCodes;

    /**
     * 参与组合促销的每个商品的数量要求
     */
    private String productQuantities;

    /**
     * 数量组合每种商品至少需要购买的数量
     */
    private String minPurchaseQuantities;

    /**
     * 组合价
     */
    private Integer combinationPrice;

    /**
     * 组合数量
     *
     * @see QuantityCombinationPromotion#combinationQuantity
     */
    private Integer combinationQuantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public String getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(String productCodes) {
        this.productCodes = productCodes;
    }

    public String getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(String productQuantities) {
        this.productQuantities = productQuantities;
    }

    public Integer getCombinationPrice() {
        return combinationPrice;
    }

    public void setCombinationPrice(Integer combinationPrice) {
        this.combinationPrice = combinationPrice;
    }

    public String getMinPurchaseQuantities() {
        return minPurchaseQuantities;
    }

    public void setMinPurchaseQuantities(String minPurchaseQuantities) {
        this.minPurchaseQuantities = minPurchaseQuantities;
    }

    public Integer getCombinationQuantity() {
        return combinationQuantity;
    }

    public void setCombinationQuantity(Integer combinationQuantity) {
        this.combinationQuantity = combinationQuantity;
    }

    public CombinationPromotionTypeEnum getCombinationPromotionType() {
        return combinationPromotionType;
    }

    public void setCombinationPromotionType(CombinationPromotionTypeEnum combinationPromotionType) {
        this.combinationPromotionType = combinationPromotionType;
    }
}
