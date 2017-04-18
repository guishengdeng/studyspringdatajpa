package com.biz.gbck.vo.product.backend;

import com.biz.gbck.vo.IRequestVo;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author 江南
 * @date 2017/1/24
 * @reviewer
 */
public class SearchVendorCascadeVo implements IRequestVo {

    private static final long serialVersionUID = 3857760663037504647L;


    /**
     * 商品ID
     */
    @NotNull(message = "商品 ID 不能为空")
    private Long productId;

    /**
     * 商家 Id
     */
    private Long vendorId;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
