package com.biz.gbck.dao.redis.ro.stock;

import com.biz.gbck.common.Constant;
import com.biz.redis.annotation.RedisWriteIgnore;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

import java.sql.Timestamp;

/**
 * 合伙人库存 全量
 *
 * @author lei
 */
@Ro(key = "stock:partner:lock")
public class PartnerStockLockRo extends BaseRedisObject<String> {

    /**
     * stock:partner:lock:partnerId:productId
     */
    private String id;

    /**
     * 合伙人编号
     **/
    private Long partnerId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 当前库存 库存全量
     */
    @RedisWriteIgnore(type = RedisWriteIgnore.IgnoreType.NULL)
    private Integer quantity = 0;

    public PartnerStockLockRo() {
    }

    public PartnerStockLockRo(Long partnerId, Long productId, long stock, Timestamp updateTimestamp) {
        this();
        this.id = String.format("%s%s%s", partnerId, Constant.SEPARATOR, productId);
        this.partnerId = partnerId;
        this.productId = productId;
        this.quantity = stock < 0 || Math.abs(stock) > Integer.MAX_VALUE ? 0 : (int) stock;
        this.setUpdateTimestamp(updateTimestamp);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
