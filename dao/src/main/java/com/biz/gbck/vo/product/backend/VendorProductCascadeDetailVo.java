package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * @author 江南
 * @date 2017/1/24
 * @reviewer
 */
public class VendorProductCascadeDetailVo implements Serializable {

    private static final long serialVersionUID = -1938337331850646997L;

    /**
     * 主配置商品ID
     */
    private String productId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 配置商品信息
     */
    private List<ProductIdExtendPropertyIdVo> productIdExtendPropertyIdVos;


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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductIdExtendPropertyIdVo> getProductIdExtendPropertyIdVos() {
        return productIdExtendPropertyIdVos;
    }

    public void setProductIdExtendPropertyIdVos(List<ProductIdExtendPropertyIdVo> productIdExtendPropertyIdVos) {
        this.productIdExtendPropertyIdVos = productIdExtendPropertyIdVos;
    }
}
