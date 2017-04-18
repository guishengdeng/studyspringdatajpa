package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * @author 江南
 * @date 2017/2/4
 * @reviewer
 */
public class ProductCascadeException extends DepotNextDoorException {

    private static final long serialVersionUID = 4057336379560351137L;

    private static final int code = DepotNextDoorExceptions.Product.CASCADE_ERROR.getCode();

    public ProductCascadeException(String message) {
        super(message);
        super.setCode(code);
    }
}
