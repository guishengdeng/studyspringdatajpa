package com.biz.gbck.exceptions.cart;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 购物车参数不合法
 *
 * @author lei
 * @date 2017年05月02日
 * @reviewer
 * @see
 */
public class CartItemNotExistException extends DepotNextDoorException {
    private static final long serialVersionUID = 6191216245233367781L;

    private static final int code = DepotNextDoorExceptions.Cart.CART_ITEM_NOT_EXIST.getCode();

    public CartItemNotExistException(String message) {
        super(message);
        super.setCode(code);
    }
}
