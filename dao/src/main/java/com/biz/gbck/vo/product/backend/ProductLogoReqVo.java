package com.biz.gbck.vo.product.backend;

import com.biz.gbck.vo.IRequestVo;
import java.util.List;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/8
 */
public class ProductLogoReqVo implements IRequestVo {

    private static final long serialVersionUID = 6913551676640147409L;

    private List<Long> productId;

    private Long vendorId;

    public ProductLogoReqVo(List<Long> productId, Long vendorId) {
        this.productId = productId;
        this.vendorId = vendorId;
    }

    public ProductLogoReqVo() {
    }

    public List<Long> getProductId() {
        return productId;
    }

    public void setProductId(List<Long> productId) {
        this.productId = productId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}
