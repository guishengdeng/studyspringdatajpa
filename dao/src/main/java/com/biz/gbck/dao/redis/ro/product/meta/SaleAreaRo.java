package com.biz.gbck.dao.redis.ro.product.meta;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;

/**
 * 销售区域 Ro(Id为区域编码 + 商家 ID)
 *
 * @author david-liu
 * @date 2016年12月30日
 * @reviewer
 * @see
 */
@Ro(key = "product:SaleAreaRo")
public class SaleAreaRo extends BaseRedisObject<String> implements Serializable {

    private static final long serialVersionUID = -2997083628996008756L;

    /**
     * 名称
     */
    private String name;

    /**
     * 区域编码
     */
    private String code;

    /**
     * 销售区域描述
     */
    private String description;

    /**
     * 商家 ID
     */
    private Long vendorId;

    /**
     * 启用状态
     */
    private CommonStatusEnum status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
