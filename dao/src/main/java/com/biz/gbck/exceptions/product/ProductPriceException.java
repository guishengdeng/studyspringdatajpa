package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 商品价格异常
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public class ProductPriceException extends DepotNextDoorException {
    private static final long serialVersionUID = 443872815362551446L;

    private static final int code = DepotNextDoorExceptions.Product.INVALID_PRICE.getCode();

    public ProductPriceException(String message) {
        super(message);
        super.setCode(code);
    }
}
