package com.biz.gbck.exceptions.cart;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 购物车商品无效
 *
 * @author lei
 * @date 2017年05月02日
 * @reviewer
 * @see
 */
public class CartItemProductInvalidException extends DepotNextDoorException {
    private static final long serialVersionUID = 6191216245233367781L;

    private static final int code = DepotNextDoorExceptions.Cart.CART_ITEM_INVALID_EXIST.getCode();

    public CartItemProductInvalidException(String message) {
        super(message);
        super.setCode(code);
    }
}
