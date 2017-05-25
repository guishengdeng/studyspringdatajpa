package com.biz.gbck.vo.cart;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;

import javax.validation.constraints.NotNull;

/**
 * 更新购物车数量
 *
 * @author lei
 * @date 2017年05月25日
 * @reviewer
 * @see
 */
public class ShopCartItemUpdateReqVo extends CommonReqVoBindUserId {

    /**
     * 商品编码
     */
    @NotNull(message = "productId不能为空")
    private String productId;

    /**
     * 数量
     */
    @NotNull(message = "quantity不能为空")
    private Integer quantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
