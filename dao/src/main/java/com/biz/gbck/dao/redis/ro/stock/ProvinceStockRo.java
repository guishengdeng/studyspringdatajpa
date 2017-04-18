package com.biz.gbck.dao.redis.ro.stock;

import com.biz.core.util.DateUtil;
import com.biz.gbck.common.Constant;
import com.biz.redis.annotation.RedisWriteIgnore;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 省份商品库存 全量
 * key = stock:province:provinceId:productCode
 *
 * @author 磊
 * @date 2016年12月5日
 */
@Ro(key = "stock:province")
@RoSortedSet(key = "list", score = "createTimestamp")
public class ProvinceStockRo extends BaseRedisObject<String> {

    /**
     * stock:province:provinceId:productCode
     */
    private String id;

    /**
     * 省份id
     */
    private Integer provinceId;

    /**
     * sku编码
     */
    private String productCode;

    /**
     * 当前库存 库存全量
     */
    @RedisWriteIgnore(type = RedisWriteIgnore.IgnoreType.NULL)
    private Integer quantity = 0;

    public ProvinceStockRo() {
        super();
    }

    public ProvinceStockRo(Integer provinceId, String productCode, Integer quantity) {
        this();
        this.id = String.format("%s%s%s", provinceId, Constant.SEPARATOR, productCode);
        this.provinceId = provinceId;
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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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
