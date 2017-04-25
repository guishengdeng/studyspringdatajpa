package com.biz.vo.cart;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 修改购物车明细vo
 *
 * @author lei
 * @date 2017/01/12
 */
public class ShopCartItemUpdateReqVo implements Serializable {

    private static final long serialVersionUID = -1730115632054116788L;

    /**
     * 商品编码
     */
    private String pCode;

    /**
     * 数量
     */
    private int quantity = 1;

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
