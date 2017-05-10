package com.biz.gbck.dao.redis.ro.product.price;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 商品价格组RO
 *
 * Created by david-liu on 2017/05/03 15:55.
 */
@Ro(key = "pro:priceGroupRO")
@RoSortedSet(key = "list", score = "createTimestamp")
public class PriceGroupRO extends BaseRedisObject<Long> {
    private static final long serialVersionUID = -2890885146481670728L;

    /**
     * 价格组ID
     */
    private Long priceGroupId;

    /**
     * 上级采购单位ID
     */
    private Long sellerId;

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
        return "PriceGroupRO{" +
                "priceGroupId=" + priceGroupId +
                ", sellerId=" + sellerId +
                '}';
    }
}
