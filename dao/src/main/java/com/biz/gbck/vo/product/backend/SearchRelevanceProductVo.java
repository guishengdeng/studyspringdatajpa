package com.biz.gbck.vo.product.backend;

import com.biz.gbck.vo.IRequestVo;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author 江南
 * @date 2017/1/24
 * @reviewer
 */
public class SearchRelevanceProductVo implements IRequestVo {

    private static final long serialVersionUID = -1125630479973062810L;

    /**
     * 关联商品 ID
     */
    @NotNull(message = "关联商品编码不能为空")
    private String productCode;

    /**
     * 商家 Id
     */
    private Long vendorId;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
