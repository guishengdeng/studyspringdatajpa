package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;

/**
 * @author david-liu
 * @date 2017年03月06日
 * @reviewer
 */
public class ProductPropertyReqVo implements Serializable {
    private static final long serialVersionUID = 8511387850629285460L;

    /**
     * 商品编码
     */
    private String productCode;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
