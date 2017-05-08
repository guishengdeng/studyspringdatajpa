package com.biz.gbck.vo.product.promotion.soa.singleProduct.response;

import com.biz.gbck.enums.product.promotion.SingleProductPromotionTypeEnum;
import com.biz.gbck.vo.product.promotion.soa.singleProduct.IProductSingleProductPromotionVo;

/**
 * 单品促销RespItemVo
 *
 * Created by david-liu on 2017/04/27 11:55.
 */
public class SingleProductPromotionRespItemVo implements IProductSingleProductPromotionVo {
    private static final long serialVersionUID = 4239470598112868884L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 促销Id
     */
    private Long promotionId;

    /**
     * 促销描述信息
     */
    private String promotionDescription;

    /**
     * 单品促销类型
     */
    private SingleProductPromotionTypeEnum promotionType;

    /**
     * 是否和优惠券使用互斥
     */
    private Boolean isExclusiveWithVoucher;

    @Override
    public Long getPromotionId() {
        return this.promotionId;
    }

    @Override
    public SingleProductPromotionTypeEnum getPromotionType() {
        return this.promotionType;
    }

    @Override
    public String getPromotionDescription() {
        return this.promotionDescription;
    }

    @Override
    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public void setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }

    public void setPromotionType(SingleProductPromotionTypeEnum promotionType) {
        this.promotionType = promotionType;
    }

    public Boolean getExclusiveWithVoucher() {
        return isExclusiveWithVoucher;
    }

    public void setExclusiveWithVoucher(Boolean exclusiveWithVoucher) {
        isExclusiveWithVoucher = exclusiveWithVoucher;
    }
}
