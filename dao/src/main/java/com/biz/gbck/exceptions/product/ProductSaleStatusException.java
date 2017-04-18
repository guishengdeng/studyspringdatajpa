package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * @author 江南
 * @date 2017/1/17
 * @reviewer
 */
public class ProductSaleStatusException extends DepotNextDoorException {

    private static final long serialVersionUID = -6540755546009206710L;

    private static final int code = DepotNextDoorExceptions.Product.SALE_STATUS_ERROR.getCode();

    public ProductSaleStatusException(String message) {
        super(message);
        super.setCode(code);
    }
}
