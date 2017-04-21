package com.biz.soa.product.service.interfaces.impl;

import com.biz.gbck.dao.redis.ro.product.bbc.ProductRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.soa.product.service.interfaces.ProductValidator;
import org.codelogger.utils.ValueUtils;

/**
 * B类商品用户足迹列表验证器
 *
 * @author david-liu
 * @date 2017年01月18日
 * @reviewer
 */
public class TypeBUserFootPrintProductItemValidator implements ProductValidator {
    private static final long serialVersionUID = -3298012925466423784L;

    volatile private static ProductValidator instance = null;

    private TypeBUserFootPrintProductItemValidator() {

    }

    public static ProductValidator getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (TypeBUserFootPrintProductItemValidator.class) {
                if (instance == null) {
                    instance = new TypeBUserFootPrintProductItemValidator();
                }
                return instance;
            }
        }
    }

    @Override
    public Boolean doValidate(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) {
        if (productPrototype == null) {
            return Boolean.FALSE;
        }
        ProductRo productRo = productPrototype.getProductRo();
        Boolean productValid = productRo != null;
        Boolean priceValid;
        if (productPrototype.getDepotCode() != null) {
            priceValid = ValueUtils.getValue(productPrototype.getFinalPrice(userLevel)) > 0 &&
                    ValueUtils.getValue(productPrototype.getMarketPrice()) > 0;
        } else {
            priceValid = ValueUtils.getValue(productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel)) > 0 &&
                    ValueUtils.getValue(productPrototype.getWarehouseDepotMarketPrice()) > 0;
        }
        return productValid && priceValid && productValid;
    }

    @Override
    public void doValidateWithException(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) throws DepotNextDoorException {
        if (!this.doValidate(productPrototype, userLevel, validateStock)) {
            throw new DepotNextDoorException("商品数据校验失败");
        }
    }
}
