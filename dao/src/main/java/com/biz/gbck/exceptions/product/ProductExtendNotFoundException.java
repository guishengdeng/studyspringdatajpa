package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 商品扩展属性未找到异常
 *
 * @author david-liu
 * @date 2016年12月27日
 * @reviewer
 * @see
 */
public class ProductExtendNotFoundException extends DepotNextDoorException {

    private static final long serialVersionUID = 653467501740668697L;

    private static final int code = DepotNextDoorExceptions.Product.EDIT_DATA_ID_ERROR.getCode();

    public ProductExtendNotFoundException(String message) {
        super(message);
        super.setCode(code);
    }
}
