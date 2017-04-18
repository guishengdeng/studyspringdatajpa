package com.biz.gbck.vo.vendor;

import com.biz.gbck.vo.IRequestVo;

public class VendorBrandVo implements IRequestVo {

    private static final long serialVersionUID = 6314373469989567631L;

    private String id;

    private String brandNames;

    private String brandId;

    private String categoryId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandNames() {
        return brandNames;
    }

    public void setBrandNames(String brandNames) {
        this.brandNames = brandNames;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
