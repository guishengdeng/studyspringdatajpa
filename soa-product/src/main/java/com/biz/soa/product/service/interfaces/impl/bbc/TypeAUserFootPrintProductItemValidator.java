package com.biz.soa.product.service.interfaces.impl.bbc;

import com.biz.gbck.dao.redis.ro.product.bbc.ProductRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.soa.product.service.interfaces.ProductValidator;
import org.codelogger.utils.ValueUtils;

/**
 * 用户足迹A类商品列表项商品验证器
 *
 * @author david-liu
 * @date 2017年01月18日
 * @reviewer
 */
public class TypeAUserFootPrintProductItemValidator implements ProductValidator {
    private static final long serialVersionUID = 413459929420037880L;

    volatile private static TypeAUserFootPrintProductItemValidator instance = null;

    private TypeAUserFootPrintProductItemValidator() {

    }

    public static ProductValidator getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (TypeAUserFootPrintProductItemValidator.class) {
                if (instance == null) {
                    instance = new TypeAUserFootPrintProductItemValidator();
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
        Boolean priceValid = ValueUtils.getValue(productPrototype.getMarketPrice()) > 0 &&
                ValueUtils.getValue(productPrototype.getFinalPrice(userLevel)) > 0;
        return productValid && priceValid;
    }

    @Override
    public void doValidateWithException(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock)
            throws DepotNextDoorException {
        if (!this.doValidate(productPrototype, userLevel, validateStock)) {
            throw new DepotNextDoorException("用户足迹商品校验失败");
        }
    }
}
