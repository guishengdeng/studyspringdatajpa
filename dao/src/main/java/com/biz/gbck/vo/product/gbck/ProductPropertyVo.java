package com.biz.gbck.vo.product.gbck;

import java.io.Serializable;

/**
 * 商品属性Vo
 *
 * Created by david-liu on 2017/04/28 14:42.
 */
public class ProductPropertyVo implements Serializable {
    private static final long serialVersionUID = -25991995117057993L;

    private String title;

    private String value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
