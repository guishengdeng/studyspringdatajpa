package com.biz.gbck.vo.price;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 商品价格ReqVo
 *
 * Created by david-liu on 2017/05/02 11:33.
 */
public class PriceGroupProductCodesPriceReqVO implements Serializable {
    private static final long serialVersionUID = 7310166382112876563L;

    /**
     * 价格组Id
     */
    @NotNull(message = "商品价格组ID不能为空")
    private Long priceGroupId;

    /**
     * 商品编码集合
     */
    @NotNull(message = "商品ID集合不能为空")
    @NotEmpty(message = "商品ID集合不能为空")
    private List<Long> productIds;

    public PriceGroupProductCodesPriceReqVO(Long priceGroupId, List<Long> productIds) {
        this.priceGroupId = priceGroupId;
        this.productIds = productIds;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "PriceGroupProductCodesPriceReqVO{" +
                "priceGroupId=" + priceGroupId +
                ", productIds=" + productIds +
                '}';
    }
}
