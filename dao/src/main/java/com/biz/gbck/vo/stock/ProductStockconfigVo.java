package com.biz.gbck.vo.stock;

import com.biz.gbck.dao.mysql.po.product.meta.Brand;
import com.biz.gbck.dao.mysql.po.product.meta.Category;

import java.io.Serializable;

/**
 * 调用product中的属性
 * Created by lzz on 2017/5/18.
 */
public class ProductStockconfigVo implements Serializable{

    private static final long serialVersionUID = 275993033651074411L;

    /**
     * 商品品牌
     */
    private Brand brand;

    /**
     * 商品分类
     */
    private Category category;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
