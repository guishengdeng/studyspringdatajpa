package com.biz.gbck.vo.product.promotion;

import com.biz.gbck.enums.product.promotion.PromotionTypeEnum;
import java.io.Serializable;

/**
 * 订单生效的促销明细VO
 *
 * Created by david-liu on 2017/05/24 12:13.
 */
public class OrderActivePromotionItemVO implements Serializable {
    private static final long serialVersionUID = 4735139776095780312L;

    /**
     * 促销ID
     */
    private Long id;

    /**
     * 促销名称
     */
    private String name;

    /**
     * 促销描述
     */
    private String description;

    /**
     * 促销类型
     */
    private PromotionTypeEnum promotionType;

    /**
     * 促销影响的订单商品ID
     */
    private Long productId;

    /**
     * 订单商品的数量
     */
    private Integer productQuantity;

    /**
     * 促销影响的商品原始订单金额
     */
    private Integer orderProductOriginAmount;

    /**
     * 促销影响的商品促销减免金额
     */
    private Integer orderProductCutAmount;

    /**
     * 订单商品促销减免后金额
     */
    private Integer orderProductAmountAfterPromotion;

    /**
     * 促销减免金额后是否可继续使用优惠券
     */
    private Boolean allowVoucher;

    /**
     * 满赠促销的赠品ID
     */
    private Long giftProductId;

    /**
     * 满赠促销的商品是否受库存限制
     */
    private Boolean isGiftLimitStock;

    /**
     * 促销影响的商品数量
     */
    private Integer promotionEffectProductQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PromotionTypeEnum getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionTypeEnum promotionType) {
        this.promotionType = promotionType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getOrderProductOriginAmount() {
        return orderProductOriginAmount;
    }

    public void setOrderProductOriginAmount(Integer orderProductOriginAmount) {
        this.orderProductOriginAmount = orderProductOriginAmount;
    }

    public Integer getOrderProductCutAmount() {
        return orderProductCutAmount;
    }

    public void setOrderProductCutAmount(Integer orderProductCutAmount) {
        this.orderProductCutAmount = orderProductCutAmount;
    }

    public Integer getOrderProductAmountAfterPromotion() {
        return orderProductAmountAfterPromotion;
    }

    public void setOrderProductAmountAfterPromotion(Integer orderProductAmountAfterPromotion) {
        this.orderProductAmountAfterPromotion = orderProductAmountAfterPromotion;
    }

    public Boolean getAllowVoucher() {
        return allowVoucher;
    }

    public void setAllowVoucher(Boolean allowVoucher) {
        this.allowVoucher = allowVoucher;
    }

    public Long getGiftProductId() {
        return giftProductId;
    }

    public void setGiftProductId(Long giftProductId) {
        this.giftProductId = giftProductId;
    }

    public Boolean getGiftLimitStock() {
        return isGiftLimitStock;
    }

    public void setGiftLimitStock(Boolean giftLimitStock) {
        isGiftLimitStock = giftLimitStock;
    }

    public Integer getPromotionEffectProductQuantity() {
        return promotionEffectProductQuantity;
    }

    public void setPromotionEffectProductQuantity(Integer promotionEffectProductQuantity) {
        this.promotionEffectProductQuantity = promotionEffectProductQuantity;
    }
}
