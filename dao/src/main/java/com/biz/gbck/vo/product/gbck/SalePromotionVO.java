package com.biz.gbck.vo.product.gbck;

import java.io.Serializable;

/**
 * 促销VO
 * Created by david-liu on 2017/05/14 23:52.
 */
public class SalePromotionVO implements Serializable {
    private static final long serialVersionUID = -3874913184970607500L;

    /**
     * 促销ID
     */
    private String id;

    /**
     * 促销名称
     */
    private String name;

    /**
     * 促销描述
     */
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
