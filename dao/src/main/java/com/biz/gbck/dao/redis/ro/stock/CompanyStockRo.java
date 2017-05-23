package com.biz.gbck.dao.redis.ro.stock;

import com.biz.core.util.DateUtil;
import com.biz.gbck.common.Constant;
import com.biz.redis.annotation.RedisWriteIgnore;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 公司锁定库存
 *
 * @author lei
 */
@Ro(key = "stock:company")
public class CompanyStockRo extends BaseRedisObject<String> {

    /**
     * stock:company:companyId:productId
     */
    private String id;

    /**
     * 合伙人编号
     **/
    private Long companyId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 当前库存 库存全量
     */
    @RedisWriteIgnore(type = RedisWriteIgnore.IgnoreType.NULL)
    private Integer quantity = 0;

    public CompanyStockRo() {
    }

    public CompanyStockRo(Long companyId, Long productId, Integer quantity) {
        this();
        this.id = String.format("%s%s%s", companyId, Constant.SEPARATOR, productId);
        this.companyId = companyId;
        this.productId = productId;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
