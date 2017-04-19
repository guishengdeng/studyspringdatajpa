package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/8
 */
public class ProductLogoVo implements Serializable {

    private static final long serialVersionUID = -8988217992217744722L;

    private String productCode;

    private String logo;

    private String name;

    public ProductLogoVo(String productCode, String logo, String name) {
        this.productCode = productCode;
        this.logo = logo;
        this.name = name;
    }

    public ProductLogoVo() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
