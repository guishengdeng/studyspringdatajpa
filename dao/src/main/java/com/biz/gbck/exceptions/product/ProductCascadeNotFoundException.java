package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 商品配置已经存在异常
 *
 * @author david-liu
 * @date 2016年12月28日
 * @reviewer
 * @see
 */
public class ProductCascadeNotFoundException extends DepotNextDoorException {

    private static final long serialVersionUID = -1635900889989004728L;

    private static final int code = DepotNextDoorExceptions.Product.CASCADE_PRICE.getCode();

    public ProductCascadeNotFoundException(String message) {
        super(message);
        super.setCode(code);
    }
}
