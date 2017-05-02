package com.biz.manage.vo.product;

import java.io.Serializable;

/**
 * 产品价格管理列表VO
 * @author xs
 *
 */
public class ProductPriceVO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -5443060684993856720L;
    /**
     * 主键
     */
    private String id;
    /**
     * 商品编码
     */
    private String productCode;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 采购价
     */
    private Integer purchasePrice;
    /**
     * 销售价
     */
    private Integer salePrice;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public Integer getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public Integer getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }
}
