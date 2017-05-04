package com.biz.gbck.vo.order.req;

import com.biz.gbck.vo.IRequestVo;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 订单商品明细Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderSettlePageItemReqVo implements IRequestVo {

    private static final long serialVersionUID = -5741991657192871333L;

    /**
     * 商品编码
     */
    private String pCode;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 商品价格
     */
    @JsonIgnore
    private Integer price;


    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
