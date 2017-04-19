package com.biz.gbck.vo.product.backend;

import com.biz.gbck.vo.IRequestVo;

/**
 * @author 江南
 * @date 2017/1/24
 * @reviewer
 */
public class SoldOutProductVo implements IRequestVo {

    private static final long serialVersionUID = 4304937288256296212L;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商家ID
     */
    private String vendorId;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
