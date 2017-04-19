package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/23
 */
public class SaleTagNotFoundException extends DepotNextDoorException {

    public SaleTagNotFoundException(String message) {
        super(message);
    }
}
