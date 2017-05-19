package com.biz.gbck.vo.stock;

import com.biz.gbck.dao.mysql.po.product.meta.Brand;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.enums.org.CompanyLevel;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 库存
 * Created by lzz on 2017/5/17.
 */
public class StockShowVo implements Serializable {

    private static final long serialVersionUID = 4812282780325449250L;

    private Long id;

    /**
     * 隔壁仓库、省公司(平台公司)、合伙人
     */
    private Long companyId;

    /**
     * 公司名称(冗余)
     */
    private String companyName;

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 库存数量
     */
    private Integer quantity;

    /**
     * 商品品牌
     */
    private Brand brand;


    /**
     * 规格
     */
    private String standard;

    /**
     * 商品分类
     */
    private Category category;

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}