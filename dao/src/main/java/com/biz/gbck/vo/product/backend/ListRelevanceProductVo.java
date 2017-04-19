package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.product.SaleStatusEnum;
import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/24
 */
public class ListRelevanceProductVo implements Serializable {

    private static final long serialVersionUID = 2512002375200581935L;

    private String productCode;

    private String productName;

    private SaleStatusEnum saleStatus;

    private String categoryName;

    public ListRelevanceProductVo() {
    }

    public ListRelevanceProductVo(String productCode, String productName, SaleStatusEnum saleStatus, String categoryName) {
        this.productCode = productCode;
        this.productName = productName;
        this.saleStatus = saleStatus;
        this.categoryName = categoryName;
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

    public SaleStatusEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatusEnum saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
