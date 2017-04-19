package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 配置商品请求Vo
 *
 * @author david-liu
 * @date 2017年02月16日
 * @reviewer
 */
public class ProductCascadeReqVo implements Serializable {
    private static final long serialVersionUID = 8704279732035323131L;

    /**
     * 商户ID
     */
    private Long vendorId;

    /**
     * 商品编码
     */
    private String productCode;

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
