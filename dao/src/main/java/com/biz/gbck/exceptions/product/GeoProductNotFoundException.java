package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * @author 江南
 * @date 2017/1/17
 * @reviewer
 */
public class GeoProductNotFoundException extends DepotNextDoorException {

    private static final long serialVersionUID = 926299426642484285L;

    private static final int code = DepotNextDoorExceptions.Product.GEO_PRODUCT_NOT_EXISTS.getCode();

    public GeoProductNotFoundException(String message) {
        super(message);
        super.setCode(code);
    }
}
