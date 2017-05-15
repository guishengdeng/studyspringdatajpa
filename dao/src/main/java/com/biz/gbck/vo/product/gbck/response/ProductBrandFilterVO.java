package com.biz.gbck.vo.product.gbck.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by david-liu on 2017/05/10 16:14.
 */
public class ProductBrandFilterVO implements Serializable {
    private static final long serialVersionUID = -1223523524294417514L;

    private String field = "brand";

    private List<ProductBrandFilterItemVO> items;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<ProductBrandFilterItemVO> getItems() {
        return items;
    }

    public void setItems(List<ProductBrandFilterItemVO> items) {
        this.items = items;
    }
}
