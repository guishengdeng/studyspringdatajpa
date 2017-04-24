package com.biz.gbck.vo.product.backend;

import com.biz.gbck.vo.IRequestVo;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/24
 */
public class PutAwayReqVo implements IRequestVo {

    private static final long serialVersionUID = 7463721621549136914L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商家ID
     */
    private String vendorId;

    public PutAwayReqVo(String productCode, String vendorId) {
        this.productCode = productCode;
        this.vendorId = vendorId;
    }

    public PutAwayReqVo() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
