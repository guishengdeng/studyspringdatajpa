package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 商品数据出错异常(使用场景如: 商品类型字段不合法, 商品价格数据不存在)
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public class ProductFatalException extends DepotNextDoorException {
    private static final long serialVersionUID = -4772345713398430571L;

    private static final int code = DepotNextDoorExceptions.Product.INTERNAL_ERROR.getCode();

    public ProductFatalException(String message) {
        super(message);
        super.setCode(code);
    }
}
