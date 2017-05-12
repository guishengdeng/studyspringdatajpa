package com.biz.gbck.vo.product.gbck.response;

import java.io.Serializable;

/**
 * 商品品牌过滤条件项VO
 *
 * Created by david-liu on 2017/05/10 16:15.
 */
public class ProductBrandFilterItemVO implements Serializable {
    private static final long serialVersionUID = 5462842863223738682L;

    private String label;

    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
