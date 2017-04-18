package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

public class AllProductExtendVo implements Serializable {

    private static final long serialVersionUID = 8767643156768901627L;

    private Long categoryId;

    private Long productExtendId;

    private String name;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductExtendId() {
        return productExtendId;
    }

    public void setProductExtendId(Long productExtendId) {
        this.productExtendId = productExtendId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
