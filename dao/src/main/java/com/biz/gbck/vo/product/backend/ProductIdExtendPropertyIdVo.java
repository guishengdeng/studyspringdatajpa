package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @date 2017/1/24
 * @reviewer
 */
public class ProductIdExtendPropertyIdVo implements Serializable {

    private static final long serialVersionUID = 1010008350968890337L;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 扩展属性值ID
     */
    private String extendPropertyId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 扩展属性ID
     */
    private String productExtendId;


    public ProductIdExtendPropertyIdVo(String productId, String extendPropertyId, String productName) {
        this.productId = productId;
        this.extendPropertyId = extendPropertyId;
        this.productName = productName;
    }

    public ProductIdExtendPropertyIdVo(String productId, String extendPropertyId, String productName,
                                       String productExtendId) {
        this.productId = productId;
        this.extendPropertyId = extendPropertyId;
        this.productName = productName;
        this.productExtendId = productExtendId;
    }

    public ProductIdExtendPropertyIdVo() {
    }

    public String getProductExtendId() {
        return productExtendId;
    }

    public void setProductExtendId(String productExtendId) {
        this.productExtendId = productExtendId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getExtendPropertyId() {
        return extendPropertyId;
    }

    public void setExtendPropertyId(String extendPropertyId) {
        this.extendPropertyId = extendPropertyId;
    }
}
