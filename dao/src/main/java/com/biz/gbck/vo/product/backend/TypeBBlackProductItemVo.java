package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/3/9
 */
public class TypeBBlackProductItemVo implements Serializable {

    private String productName;

    private String productCode;

    public TypeBBlackProductItemVo(String productName, String productCode) {
        this.productName = productName;
        this.productCode = productCode;
    }

    public TypeBBlackProductItemVo() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
