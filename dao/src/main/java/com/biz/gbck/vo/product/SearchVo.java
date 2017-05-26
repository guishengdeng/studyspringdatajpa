package com.biz.gbck.vo.product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 搜索商品VO
 * Created by lzz on 2017/5/19.
 */
public class SearchVo implements Serializable {

    private static final long serialVersionUID = -2203231226165706575L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 规格
     */
    private String categoryName;

    /**
     * 公司ID
     */
    private Long companyId;

    /**
     * 登录用户
     */
    private String userName;

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
