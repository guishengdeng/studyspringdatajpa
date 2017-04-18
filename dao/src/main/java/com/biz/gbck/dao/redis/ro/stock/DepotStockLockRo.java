package com.biz.gbck.dao.redis.ro.stock;

import com.biz.core.util.DateUtil;
import com.biz.gbck.common.Constant;
import com.biz.redis.annotation.RedisWriteIgnore;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 门店库存 锁定量
 *
 * @author lei
 */
@Ro(key = "stock:depot:lock")
public class DepotStockLockRo extends BaseRedisObject<String> {

    /**
     * stock:depot:depotCode:productCode
     */
    private String id;

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

    public DepotStockLockRo() {
        super();
    }

    public DepotStockLockRo(String depotCode, String productCode, Integer quantity) {
        this();
        this.id = String.format("%s%s%s", depotCode, Constant.SEPARATOR, productCode);
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
