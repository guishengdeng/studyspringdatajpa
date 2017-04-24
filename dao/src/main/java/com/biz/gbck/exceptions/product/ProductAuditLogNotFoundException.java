package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;

/**
 * 商品审核记录未找到异常
 *
 * @author david-liu
 * @date 2016年12月23日
 * @reviewer
 * @see
 */
public class ProductAuditLogNotFoundException extends DepotNextDoorException {

    private static final long serialVersionUID = 2503560489321135247L;

    private static final int code = DepotNextDoorExceptions.Product.LOG_NOT_ERROR.getCode();

    public ProductAuditLogNotFoundException(String message) {
        super(message);
        super.setCode(code);
    }
}
