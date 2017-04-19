package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 品牌未找到 Exception
 *
 * @author david-liu
 * @date 2016年12月21日
 * @reviewer
 * @see
 */
public class BrandNotFoundException extends DepotNextDoorException {
    private static final long serialVersionUID = 6133216245233367781L;

    private static final int code = DepotNextDoorExceptions.Product.BRAND_NOT_EXISTS.getCode();

    public BrandNotFoundException(String message) {
        super(message);
        super.setCode(code);
    }
}
