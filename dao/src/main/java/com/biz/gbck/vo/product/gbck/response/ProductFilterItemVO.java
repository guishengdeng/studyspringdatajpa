package com.biz.gbck.vo.product.gbck.response;

import java.io.Serializable;

/**
 * 商品过滤条件项VO
 *
 * Created by david-liu on 2017/05/10 16:11.
 */
public class ProductFilterItemVO implements Serializable {
    private static final long serialVersionUID = 5319154014406420835L;

    private String id;

    private String label;

    private String value;

    private String prefix;

    private Boolean highlightShow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Boolean getHighlightShow() {
        return highlightShow;
    }

    public void setHighlightShow(Boolean highlightShow) {
        this.highlightShow = highlightShow;
    }
}
