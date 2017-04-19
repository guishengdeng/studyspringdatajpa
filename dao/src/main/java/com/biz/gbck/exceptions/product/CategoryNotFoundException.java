package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 商品分类不存在异常
 *
 * @author david-liu
 * @date 2016年12月21日
 * @reviewer
 * @see
 */
public class CategoryNotFoundException extends DepotNextDoorException {

    private static final long serialVersionUID = 3034070457002453868L;

    private static final int code = DepotNextDoorExceptions.Product.CATEGORY_NOT_EXISTS.getCode();

    public CategoryNotFoundException(String message) {
        super(message);
        super.setCode(code);
    }

}
