package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * 更新商品增量索引请求Vo
 *
 * Created by david-liu on 2017/05/03 10:30.
 */
public class UpdateProductIncrIndicesReqVo implements Serializable {
    private static final long serialVersionUID = 3123649000022293053L;

    /**
     * 商品编码
     */
    @NotNull(message = "商品编码不能为空")
    private String productCode;

    /**
     * 价格组ID
     * <p>
     * 如果指定价格组ID, 更新该商品指定价格组ID的商品文档记录;
     * 如果没指定价格组ID, 更新该商品所有价格组ID的商品文档记录
     * </p>
     */
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
}
