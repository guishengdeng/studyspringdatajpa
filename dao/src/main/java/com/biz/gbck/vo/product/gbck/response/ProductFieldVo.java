package com.biz.gbck.vo.product.gbck.response;

import java.io.Serializable;

/**
 * 商品FieldVo
 * Created by david-liu on 2017/05/02 10:35.
 */
public class ProductFieldVo<T> implements Serializable {
    private static final long serialVersionUID = -7700589911731802898L;

    private T id;

    private String name;

    public ProductFieldVo(T id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductFieldVo() {
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
