package com.biz.gbck.vo.product.gbck.request;

import java.io.Serializable;

/**
 * Created by david-liu on 2017/04/28 15:05.
 */
public class ProductAppListPromotionReqVo implements Serializable {
    private static final long serialVersionUID = -7715743586348871472L;

    /**
     * 价格组Id
     */
    private Long priceGroupId;

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }
}
