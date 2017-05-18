package com.biz.gbck.vo.product.gbck.request;

import com.biz.support.web.assist.GlobalParams;
import com.biz.support.web.assist.GlobalParamsAware;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * App商品详情请求Vo
 *
 * Created by david-liu on 2017/04/28 11:59.
 */
public class ProductAppDetailReqVo implements GlobalParamsAware, Serializable {
    private static final long serialVersionUID = 2612052700611378983L;

    /**
     * 上级采购单位Id
     */
    private Long sellerId;

    /**
     * 商品编码
     */
    @NotNull(message = "商品ID不能为空")
    private Long productId;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

    /**
     * 全局参数
     */
    private GlobalParams globalParams;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
    public GlobalParams getGlobalParams() {
        return globalParams;
    }

    @Override
    public void setGlobalParams(GlobalParams globalParams) {
        this.globalParams = globalParams;
    }

    @Override
    public String toString() {
        return "ProductAppDetailReqVo{" +
                "sellerId=" + sellerId +
                ", productId=" + productId +
                ", priceGroupId=" + priceGroupId +
                '}';
    }
}
