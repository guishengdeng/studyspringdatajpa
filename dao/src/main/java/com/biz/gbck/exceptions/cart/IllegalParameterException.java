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
public class IllegalParameterException extends DepotNextDoorException {
    private static final long serialVersionUID = 6191216245233367781L;

    private static final int code = DepotNextDoorExceptions.Cart.ILLEGAL_PARAMETER.getCode();

    public IllegalParameterException(String message) {
        super(message);
        super.setCode(code);
    }
}
