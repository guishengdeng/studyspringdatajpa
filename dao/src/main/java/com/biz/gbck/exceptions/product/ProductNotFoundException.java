package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 商品未找到异常
 *
 * @author david-liu
 * @date 2016年12月26日
 * @reviewer
 * @see
 */
public class ProductNotFoundException extends DepotNextDoorException {

    private static final long serialVersionUID = 8807321243997972519L;

    private static final int code = DepotNextDoorExceptions.Product.NOT_EXIST.getCode();

    public ProductNotFoundException(String message) {
        super(message);
        super.setCode(code);
    }
}
