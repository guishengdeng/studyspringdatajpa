package com.biz.gbck.vo.cart;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;

/**
 * 添加购物车明细vo
 *
 * @author lei
 * @date 2017/01/12
 */
public class ShopCartItemAddReqVo extends CommonReqVoBindUserId {


    /**
     * 商品编码
     */
    @NotNull(message = "productId不能为空")
    private String productId;

    /**
     * 数量
     */
    @NotNull(message = "quantity不能为空")
    private Integer quantity = 1;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
