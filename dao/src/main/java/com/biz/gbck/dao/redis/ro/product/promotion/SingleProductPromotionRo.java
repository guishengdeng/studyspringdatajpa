package com.biz.gbck.dao.redis.ro.product.promotion;

import com.biz.gbck.dao.mysql.po.product.price.PriceGroup;
import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.BatchSpecialOfferPromotion;
import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.MultipleQuantityPromotion;
import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.QuantitySpecialOfferPromotion;
import com.biz.gbck.dao.mysql.po.product.promotion.singleProduct.SinglePurchaseGiftPromotion;
import com.biz.gbck.dao.redis.ro.product.master.ProductRo;
import com.biz.gbck.enums.product.promotion.SingleProductPromotionTypeEnum;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 单品促销Ro
 * <p>
 * Ro的ID为商品ID + 价格组ID, 将除了简单特价之外的单品促销的所有信息记入到当前Ro,
 * 方便在取促销Ro时批量取出, 以单品促销枚举{@link SingleProductPromotionTypeEnum}区分单品促销的类型
 * </p>
 *
 * Created by david-liu on 2017/04/25 14:36.
 */
@Ro(key = "pro:promotion:singleProductPromotionRo")
@RoSortedSet(key = "list")
public class SingleProductPromotionRo extends BaseRedisObject<String> {

    private static final long serialVersionUID = 8189303729064599289L;

    /**
     * 促销ID
     */
    private Long promotionId;

    /**
     * 商品编码
     *
     * @see ProductRo#productCode
     */
    private String productCode;

    /**
     * 价格组ID
     *
     * @see PriceGroup#id
     */
    private Long priceGroupId;

    /**
     * 单品促销类型
     *
     * @see SingleProductPromotionTypeEnum#value
     */
    private Integer singleProductPromotionType;

    /**
     * 促销价
     * <p>
     * 单品促销的促销价都记录到此字段
     * </p>
     *
     * @see BatchSpecialOfferPromotion#promotionPrice
     * @see MultipleQuantityPromotion#promotionPrice
     * @see QuantitySpecialOfferPromotion#promotionPrice
     * @see SinglePurchaseGiftPromotion#promotionPrice
     */
    private Integer promotionPrice;

    /**
     * 达标数量
     *
     * @see BatchSpecialOfferPromotion#quantityLimit
     * @see SinglePurchaseGiftPromotion#quantityLimit
     */
    private Integer quantityLimit;

    /**
     * 赠送数量
     *
     * @see SinglePurchaseGiftPromotion#giftQuantity
     */
    private Integer giftQuantity;

    /**
     * 赠品数量上限
     */
    private Integer giftQuantityLimit;

    /**
     * 倍数特价的幸运倍数
     *
     * @see MultipleQuantityPromotion#luckyNumber
     */
    private Integer luckyNumber;

    /**
     * 是否互斥
     *
     * @see
     */
    private Boolean isExclusive;

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

    public Integer getSingleProductPromotionType() {
        return singleProductPromotionType;
    }

    public void setSingleProductPromotionType(Integer singleProductPromotionType) {
        this.singleProductPromotionType = singleProductPromotionType;
    }

    public Integer getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Integer promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getQuantityLimit() {
        return quantityLimit;
    }

    public void setQuantityLimit(Integer quantityLimit) {
        this.quantityLimit = quantityLimit;
    }

    public Integer getGiftQuantity() {
        return giftQuantity;
    }

    public void setGiftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
    }

    public Integer getGiftQuantityLimit() {
        return giftQuantityLimit;
    }

    public void setGiftQuantityLimit(Integer giftQuantityLimit) {
        this.giftQuantityLimit = giftQuantityLimit;
    }

    public Integer getLuckyNumber() {
        return luckyNumber;
    }

    public void setLuckyNumber(Integer luckyNumber) {
        this.luckyNumber = luckyNumber;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Boolean getExclusive() {
        return isExclusive;
    }

    public void setExclusive(Boolean exclusive) {
        isExclusive = exclusive;
    }
}
