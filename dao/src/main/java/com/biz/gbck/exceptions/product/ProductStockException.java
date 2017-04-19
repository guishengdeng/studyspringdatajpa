package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 商品库存异常
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public class ProductStockException extends DepotNextDoorException {
    private static final long serialVersionUID = 147376144373459429L;

    private static final int code = DepotNextDoorExceptions.Product.WITHOUT_STOCK.getCode();

    public ProductStockException(String message) {
        super(message);
        super.setCode(code);
    }
}
