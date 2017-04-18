package com.biz.gbck.vo.product;

import java.io.Serializable;

/**
 * 组合商品信息Vo
 *
 * @author 江南
 * @reviewer
 * @since 2017/3/10
 */
public class RapidProductItemVo implements Serializable {

    private static final long serialVersionUID = -905275450354739666L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品数量
     */
    private Integer quantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
