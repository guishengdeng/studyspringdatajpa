package com.biz.gbck.vo.price;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 通过商品价格组, 商品编码获取价格请求VO
 *
 * Created by david-liu on 2017/05/03 16:14.
 */
public class PriceGroupsProductCodePriceReqVO implements Serializable {
    private static final long serialVersionUID = 9047508775983852312L;

    /**
     * 价格组ID
     */
    @NotEmpty(message = "价格组ID集合不能为空")
    private List<Long> priceGroupIds;

    /**
     * 商品编码
     */
    @NotNull(message = "商品编码集合不能为空")
    private Long productId;

    public PriceGroupsProductCodePriceReqVO() {
    }

    public PriceGroupsProductCodePriceReqVO(List<Long> priceGroupIds, Long productId) {
        this.priceGroupIds = priceGroupIds;
        this.productId = productId;
    }

    public List<Long> getPriceGroupIds() {
        return priceGroupIds;
    }

    public void setPriceGroupIds(List<Long> priceGroupIds) {
        this.priceGroupIds = priceGroupIds;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "PriceGroupsProductCodePriceReqVO{" +
                "priceGroupIds=" + priceGroupIds +
                ", productId=" + productId +
                '}';
    }
}
