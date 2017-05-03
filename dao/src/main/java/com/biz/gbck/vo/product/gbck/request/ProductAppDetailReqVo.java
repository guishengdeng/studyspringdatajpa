package com.biz.gbck.vo.product.gbck.request;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * App商品详情请求Vo
 *
 * Created by david-liu on 2017/04/28 11:59.
 */
public class ProductAppDetailReqVo implements Serializable {
    private static final long serialVersionUID = 2612052700611378983L;

    /**
     * 上级采购单位Id
     */
    @NotNull(message = "上级采购单位ID不能为空")
    private Long sellerId;

    /**
     * 商品编码
     */
    @NotNull(message = "商品编码不能为空")
    private String productCode;

    /**
     * 价格组ID
     */
    @NotNull(message = "价格组ID不能为空")
    private Long priceGroupId;

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

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "ProductAppDetailReqVo{" +
                "sellerId=" + sellerId +
                ", productCode='" + productCode + '\'' +
                ", priceGroupId=" + priceGroupId +
                '}';
    }
}
