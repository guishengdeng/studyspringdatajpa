package com.biz.gbck.vo.product.gbck.response;

import java.io.Serializable;

/**
 * 商品属性字段Vo
 *
 * Created by david-liu on 2017/04/28 09:27.
 */
public class ProductFieldVo<T> implements Serializable {
    private static final long serialVersionUID = 5488735707974606163L;

    private T id;

    private String name;

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
