package com.biz.gbck.dao.redis.ro.stock;

import com.biz.core.util.DateUtil;
import com.biz.gbck.common.Constant;
import com.biz.gbck.enums.oss.OssType;
import com.biz.redis.annotation.RedisWriteIgnore;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.sql.Timestamp;

/**
 * 门店库存 全量
 *
 * @author lei
 */
@Ro(key = "stock:depot")
public class DepotStockRo extends BaseRedisObject<String> {

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

    /**
     * 版本
     */
    private Long version;

    /**
     * 库存类型
     */
    private OssType type;

    public DepotStockRo() {
    }

    public DepotStockRo(String depotCode, String productCode, long stock, Long version, OssType type, Timestamp
            updateTimestamp) {
        this();
        this.id = String.format("%s%s%s", depotCode, Constant.SEPARATOR, productCode);
        this.depotCode = depotCode;
        this.productCode = productCode;
        this.quantity = stock < 0 || Math.abs(stock) > Integer.MAX_VALUE ? 0 : (int) stock;
        this.version = version;
        this.type = type;
        this.setUpdateTimestamp(updateTimestamp);
    }

    public DepotStockRo(String depotCode, String productCode, Long stock, Long version, OssType type) {
        this(depotCode, productCode, stock, version, type, DateUtil.now());
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public OssType getType() {
        return type;
    }

    public void setType(OssType type) {
        this.type = type;
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
