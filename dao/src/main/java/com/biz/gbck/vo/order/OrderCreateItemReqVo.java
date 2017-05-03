package com.biz.gbck.vo.order;

import com.biz.gbck.vo.IRequestVo;

/**
 * 订单返回Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderCreateItemReqVo implements IRequestVo {

    private static final long serialVersionUID = -5741991657192871333L;

    /**
     * 商品Id
     */
    private Long productId;

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
    private Integer price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

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
