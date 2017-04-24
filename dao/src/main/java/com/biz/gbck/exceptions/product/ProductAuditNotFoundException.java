package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;

/**
 * 商品审核记录未找到异常
 *
 * @author david-liu
 * @date 2016年12月26日
 * @reviewer
 * @see
 */
public class ProductAuditNotFoundException extends DepotNextDoorException {

    private static final long serialVersionUID = -185821139732785450L;

    public ProductAuditNotFoundException(String message) {
        super(message);
    }
}
