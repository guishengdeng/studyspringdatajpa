package com.biz.gbck.dao.redis.ro.stock;

import com.biz.core.util.DateUtil;
import com.biz.gbck.common.Constant;
import com.biz.redis.annotation.RedisWriteIgnore;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 城市商品库存 全量
 * key = stock:city:cityId:productCode
 *
 * @author 磊
 * @date 2016年12月5日
 */
@Ro(key = "stock:city")
public class CityStockRo extends BaseRedisObject<String> {

    /**
     * stock:city:cityId:productCode
     */
    private String id;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * sku编码
     */
    private String productCode;

    /**
     * 当前库存 库存全量
     */
    @RedisWriteIgnore(type = RedisWriteIgnore.IgnoreType.NULL)
    private Integer quantity = 0;

    public CityStockRo() {
        super();
    }

    public CityStockRo(Integer cityId, String productCode, Integer quantity) {
        this();
        this.id = String.format("%s%s%s", cityId, Constant.SEPARATOR, productCode);
        this.cityId = cityId;
        this.productCode = productCode;
        this.quantity = quantity;
        this.setUpdateTimestamp(DateUtil.now());
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
