package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 扩展属性值未发现异常
 *
 * @author david-liu
 * @date 2016年12月28日
 * @reviewer
 * @see
 */
public class ExtendPropertyNotFoundException extends DepotNextDoorException {

    private static final long serialVersionUID = -7886323834664419123L;

    private static final int code = DepotNextDoorExceptions.Product.PROPERTY_NOT_ERROR.getCode();

    public ExtendPropertyNotFoundException(String message) {
        super(message);
        super.setCode(code);
    }
}
