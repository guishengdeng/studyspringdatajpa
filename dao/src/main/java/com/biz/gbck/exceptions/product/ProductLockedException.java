package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 商品已锁定异常
 *
 * @author david-liu
 * @date 2016年12月26日
 * @reviewer
 * @see
 */
public class ProductLockedException extends DepotNextDoorException {

    private static final long serialVersionUID = -7538934596126484579L;

    private static final int code = DepotNextDoorExceptions.Product.LOCKED_ERROR.getCode();

    public ProductLockedException(String message) {
        super(message);
        super.setCode(code);
    }
}
