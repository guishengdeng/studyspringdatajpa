package com.biz.gbck.vo.cart;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 购物车数量vo
 *
 * @author lei
 * @date 2017/04/05
 */
public class ShopCartNumRespVo implements Serializable {

    /**
     * 总数量
     */
    private int cartNum = 0;

    public int getCartNum() {
        return cartNum;
    }

    public void setCartNum(int cartNum) {
        this.cartNum = cartNum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
