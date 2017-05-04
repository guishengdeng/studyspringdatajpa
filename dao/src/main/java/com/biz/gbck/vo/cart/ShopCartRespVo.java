package com.biz.gbck.vo.cart;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 购物车vo
 * @author lei
 * @date 2017/01/13
 */
public class ShopCartRespVo implements Serializable {

    /**
     * 购物车商品组
     */
    private List<ShopCartItemRespVo> items = newArrayList();

    /**
     * 购物车总数
     */
    private int cartNum = 0;

    /**
     * 选中数量
     */
    private int selectedNum = 0;

    /**
     * 购物车待支付金额
     */
    private int totalPrice = 0;

    /**
     * 节省金额
     */
    private int savedPrice = 0;

    public List<ShopCartItemRespVo> getItems() {
        return items;
    }

    public void setItems(List<ShopCartItemRespVo> items) {
        this.items = items;
    }

    public int getSelectedNum() {
        return selectedNum;
    }

    public void setSelectedNum(int selectedNum) {
        this.selectedNum = selectedNum;
    }

    public int getCartNum() {
        return cartNum;
    }

    public void setCartNum(int cartNum) {
        this.cartNum = cartNum;
    }

    public int getSavedPrice() {
        return savedPrice;
    }

    public void setSavedPrice(int savedPrice) {
        this.savedPrice = savedPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
