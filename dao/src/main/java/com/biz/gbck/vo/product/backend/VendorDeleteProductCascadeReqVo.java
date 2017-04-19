package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商户删除商品配置请求 Vo
 *
 * @author david-liu
 * @date 2016年12月29日
 * @reviewer
 * @see
 */
public class VendorDeleteProductCascadeReqVo implements Serializable {

    private static final long serialVersionUID = -9057497823374234710L;

    /**
     * 商品配置 ID
     */
    @NotNull(message = "商品配置 ID 不能为空")
    private Long id;

    /**
     * 商家 Id
     */
    @NotNull(message = "商家 ID 不能为空")
    private Long vendorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
