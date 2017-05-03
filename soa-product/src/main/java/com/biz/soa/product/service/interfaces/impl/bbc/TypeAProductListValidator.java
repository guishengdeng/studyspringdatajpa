package com.biz.soa.product.service.interfaces.impl.bbc;

import com.biz.gbck.dao.redis.ro.product.bbc.ProductRo;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.soa.product.service.interfaces.ProductValidator;
import org.codelogger.utils.ValueUtils;

/**
 * A类商品列表页商品验证器
 *
 * @author david-liu
 * @date 2017年01月17日
 * @reviewer
 * @see
 */
public class TypeAProductListValidator implements ProductValidator {

    private static final long serialVersionUID = -4403826575281526835L;

    volatile private static ProductValidator instance = null;

    private TypeAProductListValidator() {
    }

    public static ProductValidator getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (TypeAProductListValidator.class) {
                if (instance == null) {
                    instance = new TypeAProductListValidator();
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
        Integer stock = productPrototype.getCountryStock();
        if (productRo == null || stock == null) {
            return Boolean.FALSE;
        }

        // 商品是否在售
        Boolean onSale = productPrototype.getProductRo().getSaleStatus() == SaleStatusEnum.ON_SALE.getValue();
        // 商品是否有库存
        Boolean hasStock = !validateStock || productPrototype.getCountryStock() > 0;
        // 商品价格是否合法(成本价不能为空, 最低限价不能为空, 用户等级价不能为空)
        Boolean priceValid = ValueUtils.getValue(productPrototype.getMarketPrice()) > 0 &&
                ValueUtils.getValue(productPrototype.getFinalPrice(userLevel)) > 0;
        return onSale && hasStock && priceValid;
    }

    @Override
    public void doValidateWithException(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock)
            throws DepotNextDoorException {
        if (!this.doValidate(productPrototype, userLevel, validateStock)) {
            throw new DepotNextDoorException("商品结果校验失败");
        }
    }
}
