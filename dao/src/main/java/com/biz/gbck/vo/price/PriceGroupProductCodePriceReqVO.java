package com.biz.gbck.vo.price;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 通过商品价格组ID, 商品
 *
 * Created by david-liu on 2017/05/02 15:12.
 */
public class PriceGroupProductCodePriceReqVO implements Serializable {
    private static final long serialVersionUID = -5639343868673627883L;

    /**
     * 价格组ID
     */
    @NotNull(message = "价格组ID不能为空")
    private Long priceGroupId;

    /**
     * 商品编码
     */
    @NotEmpty(message = "商品ID不能为空")
    private Long productId;

    /**
     * 上级采购方ID
     */
    private Long sellerId;

    public PriceGroupProductCodePriceReqVO(Long priceGroupId, Long productId, Long sellerId) {
        this.priceGroupId = priceGroupId;
        this.productId = productId;
        this.sellerId = sellerId;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
