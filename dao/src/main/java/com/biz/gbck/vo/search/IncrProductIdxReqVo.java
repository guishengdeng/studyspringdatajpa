package com.biz.gbck.vo.search;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * 商品增量索引ReqVo(只修改了商品信息)
 *
 * Created by david-liu on 2017/05/03 12:05.
 */
public class IncrProductIdxReqVo implements Serializable {
    private static final long serialVersionUID = -7024145187840135754L;

    /**
     * 商品编码
     */
    @NotNull(message = "商品编码不能为空")
    private Long productId;

    /**
     * 价格组ID
     */
    @NotNull(message = "价格组ID不能为空")
    private Long priceGroupId;

    /**
     * 上级采购单位ID
     */
    @NotNull(message = "上级采购单位ID不能为空")
    private Long sellerId;

    public IncrProductIdxReqVo(Long productId) {
        this.productId = productId;
    }

    public IncrProductIdxReqVo(Long productId, Long priceGroupId) {
        this.productId = productId;
        this.priceGroupId = priceGroupId;
    }

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
    public String toString() {
        return "IncrProductIdxReqVo{" +
                "productId='" + productId + '\'' +
                ", priceGroupId=" + priceGroupId +
                ", sellerId=" + sellerId +
                '}';
    }

}
