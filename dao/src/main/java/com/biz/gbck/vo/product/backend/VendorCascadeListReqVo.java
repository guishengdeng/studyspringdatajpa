package com.biz.gbck.vo.product.backend;

import com.biz.gbck.vo.IRequestVo;
import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/9
 */
public class VendorCascadeListReqVo implements Serializable, IRequestVo {

    private static final long serialVersionUID = 5997673473472696824L;

    private Long vendorId;

    private Long productId;

    public VendorCascadeListReqVo(Long vendorId, Long productId) {
        this.vendorId = vendorId;
        this.productId = productId;
    }

    public VendorCascadeListReqVo() {
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
