package com.biz.gbck.vo.search;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 商品索引请求Vo
 *
 * Created by david-liu on 2017/05/03 10:51.
 */
public class TotalProductIdxReqVo implements Serializable {
    private static final long serialVersionUID = 2829067707556078479L;

    /**
     * 商品编码集合
     */
    @NotEmpty(message = "商品编码集合不能为空")
    private List<String> productCodes;

    /**
     * 价格组ID
     */
    @NotNull(message = "价格组ID不能为空")
    private Long priceGroupId;

    /**
     * 上级采购方ID
     */
    @NotNull(message = "上级采购单位ID不能为空")
    private Long sellerId;

    public TotalProductIdxReqVo(List<String> productCodes, Long priceGroupId) {
        this.productCodes = productCodes;
        this.priceGroupId = priceGroupId;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
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
        return "TotalProductIdxReqVo{" +
                "productCodes=" + productCodes +
                ", priceGroupId=" + priceGroupId +
                ", sellerId=" + sellerId +
                '}';
    }
}
