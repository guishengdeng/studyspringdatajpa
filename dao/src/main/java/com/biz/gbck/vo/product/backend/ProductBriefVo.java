package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/7
 */
public class ProductBriefVo implements Serializable {

    private static final long serialVersionUID = -5218306548633349069L;

    /**
     * 商品Id
     */
    private String productId;


    /**
     * 商品编码
     */
    private String productCode;


    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品分类
     */
    private String categoryName;

    /**
     * 商品图片
     */
    private String logo;

    /**
     * 分类ID
     */
    private String categoryId;


    public ProductBriefVo(String productId, String productCode, String productName, String categoryName, String logo, String categoryId) {
        this.productCode = productCode;
        this.productName = productName;
        this.productId = productId;
        this.categoryName = categoryName;
        this.logo = logo;
        this.categoryId = categoryId;
    }

    public ProductBriefVo(String productId, String productCode, String productName, String categoryName, String logo) {
        this.productCode = productCode;
        this.productName = productName;
        this.productId = productId;
        this.categoryName = categoryName;
        this.logo = logo;
    }

    public ProductBriefVo(String productId, String productCode, String productName, String categoryName) {
        this.productCode = productCode;
        this.productName = productName;
        this.productId = productId;
        this.categoryName = categoryName;
    }

    public ProductBriefVo() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
