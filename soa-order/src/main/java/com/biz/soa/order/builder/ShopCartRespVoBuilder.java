package com.biz.soa.order.builder;

import com.biz.gbck.vo.cart.ShopCartItemRespVo;
import com.biz.gbck.vo.cart.ShopCartRespVo;

import java.util.List;

/**
 * 购物好吃返回vo Builder
 *
 * @author lei
 * @date 2017年05月19日
 * @reviewer
 * @see
 */
public class ShopCartRespVoBuilder {

    private ShopCartRespVo respVo;

    public static ShopCartRespVoBuilder createBuilder() {
        ShopCartRespVoBuilder builder = new ShopCartRespVoBuilder();
        builder.respVo = new ShopCartRespVo();
        return builder;
    }

    public ShopCartRespVoBuilder setItems(List<ShopCartItemRespVo> items) {
        this.respVo.setItems(items);
        return this;
    }

    public ShopCartRespVoBuilder setOrderAmount(Integer orderAmount) {
        this.respVo.setOrderAmount(orderAmount);
        return this;
    }

    public ShopCartRespVoBuilder setCartNum(Integer cartNum) {
        this.respVo.setCartNum(cartNum);
        return this;
    }

    public ShopCartRespVo build() {
        int payAmount = respVo.getOrderAmount() - respVo.getFreeAmount() - respVo.getVoucherAmount();
        respVo.setPayAmount(payAmount);

        return this.respVo;
    }

}
