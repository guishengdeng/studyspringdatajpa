package com.biz.gbck.dao.redis.ro.stock;

import com.biz.core.util.DateUtil;
import com.biz.redis.annotation.RedisWriteIgnore;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 商品库存 锁定量
 *
 * @author lei
 * @date 2016年8月23日
 * @reviewer
 */
@Ro(key = "stock:product:lock")
public class StockLockRo extends BaseRedisObject<String> {

    /**
     * stock:sku:id
     */
    private String id;

    /**
     * sku编码
     */
    private String productCode;

    /**
     * 当前库存 库存全量
     */
    @RedisWriteIgnore(type = RedisWriteIgnore.IgnoreType.NULL)
    private Integer quantity = 0;

    public StockLockRo() {
        super();
    }

    public StockLockRo(String productCode, Integer quantity) {
        this.id = productCode;
        this.productCode = productCode;
        this.quantity = quantity;
        this.setUpdateTimestamp(DateUtil.now());
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
