package com.biz.gbck.dao.redis.ro.stock;

import com.biz.core.util.DateUtil;
import com.biz.redis.annotation.RedisWriteIgnore;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 城市商品库存 锁定量
 * key = stock:order:lock:orderCode:depotCode:productCode
 *
 * @author 磊
 * @date 2016年12月5日
 */
@Ro(key = "stock:order:lock")
public class OrderStockLockRo extends BaseRedisObject<String> {

    /**
     * stock:order:lock:orderCode:depotCode:productCode
     */
    private String id;

    /**
     * 订单编号
     **/
    private String orderCode;

    /**
     * 门店编号
     **/
    private String depotCode;

    /**
     * sku编码
     */
    private String productCode;

    /**
     * 当前库存 库存全量
     */
    @RedisWriteIgnore(type = RedisWriteIgnore.IgnoreType.NULL)
    private Integer quantity = 0;

    public OrderStockLockRo() {
        super();
    }

    public OrderStockLockRo(String orderCode, String depotCode, String productCode, Integer quantity) {
        this();
        this.orderCode = orderCode;
        this.depotCode = depotCode;
        this.productCode = productCode;
        this.quantity = quantity;
        this.setUpdateTimestamp(DateUtil.now());
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
