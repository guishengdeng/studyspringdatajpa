package com.biz.gbck.vo.cart;

import com.biz.core.util.JsonUtil;
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
    private Integer cartNum = 0;

    /**
     * 总金额
     */
    private Integer orderAmount = 0;

    /**
     * 促销优惠免额
     */
    private Integer freeAmount = 0;

    /**
     * 优惠券优惠金额
     */
    private Integer voucherAmount = 0;

    /**
     * 优惠券优惠金额
     */
    private Integer payAmount = 0;


    public List<ShopCartItemRespVo> getItems() {
        return items;
    }

    public void setItems(List<ShopCartItemRespVo> items) {
        this.items = items;
    }

    public Integer getCartNum() {
        return cartNum;
    }

    public void setCartNum(Integer cartNum) {
        this.cartNum = cartNum;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Integer freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Integer getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(Integer voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    //选中明细,结算
    public List<ShopCartItemRespVo> getSelectedItems() {
        List<ShopCartItemRespVo> selectedItems = newArrayList();
        for (ShopCartItemRespVo item : items) {
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


    public static void main(String[] args) {
        ShopCartItemRespVo cartItemRespVo = new ShopCartItemRespVo();
        ShopCartRespVo respVo = new ShopCartRespVo();
        respVo.setItems(newArrayList(cartItemRespVo));
        JsonUtil.printObjectJsonDemo(respVo);
        System.out.println(JsonUtil.obj2Json(respVo));
    }
}
