package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;

public class ProductFilterNotFoundException extends DepotNextDoorException {

    private static final long serialVersionUID = -256969388655562992L;

    public ProductFilterNotFoundException(String message) {
        super(message);
    }

}
