package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * 商家获取关联商品的详细信息
 *
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/25
 */
public class RelevanceProductVo implements Serializable {

    private static final long serialVersionUID = -8575901697350366633L;

    /**
     * 被关联商品的编码
     */
    private String productCode;

    /**
     * 被关联商品名称
     */
    private String productName;

    /**
     * 被关联商品分类名称
     */
    private String categoryName;

    /**
     * 被关联商品的logo
     */
    private String logo;

    /**
     * 关联商品的信息
     */
    private List<RelevanceProductVo> relevanceProductVos;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<RelevanceProductVo> getRelevanceProductVos() {
        return relevanceProductVos;
    }

    public void setRelevanceProductVos(List<RelevanceProductVo> relevanceProductVos) {
        this.relevanceProductVos = relevanceProductVos;
    }
}
