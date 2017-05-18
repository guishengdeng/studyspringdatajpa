package com.biz.gbck.vo.product;

import java.io.Serializable;

/**
 * 更新索引VO
 * <p>
 * 1. 如果商品ID为空, 价格组ID不为空, 更新该价格组下的所有商品文档记录
 * 2. 如果商品ID不为空, 价格组ID为空, 更新该商品在所有价格组下的商品文档记录
 * 3. 如果商品ID为空, 价格组ID为空, 更新所有价格组下的所有商品文档记录
 * </p>
 *
 * Created by david-liu on 2017/05/15 09:41.
 */
public class UpdateProductIdxVO implements Serializable {
    private static final long serialVersionUID = -4088368915891233316L;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

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
}
