package com.biz.gbck.vo.product;

import java.io.Serializable;
import java.util.List;

/**
 * 商品属性Vo
 *
 * @author david-liu
 * @date 2017年03月06日
 * @reviewer
 */
public class ProductPropertyVo implements Serializable {
    private static final long serialVersionUID = 9175830888940116013L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 属性列表
     */
    private List<PropertyItemVo> items;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<PropertyItemVo> getItems() {
        return items;
    }

    public void setItems(List<PropertyItemVo> items) {
        this.items = items;
    }
}
