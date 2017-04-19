package com.biz.soa.product.service.interfaces;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import java.io.Serializable;

/**
 * 商品验证器接口
 *
 * @author david-liu
 * @date 2017年01月17日
 * @reviewer
 */
public interface ProductValidator extends Serializable {
    Boolean doValidate(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock);

    void doValidateWithException(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) throws DepotNextDoorException;
}
