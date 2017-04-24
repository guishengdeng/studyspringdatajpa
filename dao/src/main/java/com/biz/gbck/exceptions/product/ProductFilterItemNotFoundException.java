package com.biz.gbck.exceptions.product;

import com.biz.gbck.exceptions.DepotNextDoorException;

/**
 * 商品搜索条件未找到异常
 *
 * @author wangyumin
 * @date 2017年1月5日
 */
public class ProductFilterItemNotFoundException extends DepotNextDoorException {

    private static final long serialVersionUID = 3897605395631131675L;

    public ProductFilterItemNotFoundException(String message) {
        super(message);
    }

}
