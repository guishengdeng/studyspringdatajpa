package com.biz.gbck.vo.Inventory;

import java.io.Serializable;

/**
 * InventoryReqVo
 *
 * @author lei
 * @date 2017年05月22日
 * @reviewer
 * @see
 */
public class InventorySubmitItemReqVo implements Serializable {


    //商品编码
    private String productCode;

    //瓶码
    private String bcno;

    //箱码
    private String boxno;

    //商品数量
    private Integer quantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBcno() {
        return bcno;
    }

    public void setBcno(String bcno) {
        this.bcno = bcno;
    }

    public String getBoxno() {
        return boxno;
    }

    public void setBoxno(String boxno) {
        this.boxno = boxno;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
