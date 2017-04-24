package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 参数不符异常
 *
 * @author david-liu
 * @date 2016年12月21日
 * @reviewer
 * @see
 */
public class IllegalParameterException extends DepotNextDoorException {

    private static final long serialVersionUID = -3606588783116095836L;

    private static final int code = DepotNextDoorExceptions.Product.ILLEGAL_ERROR.getCode();

    public IllegalParameterException(String message) {
        super(message);
        super.setCode(code);
    }
}
