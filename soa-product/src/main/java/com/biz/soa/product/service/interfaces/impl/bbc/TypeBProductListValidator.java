package com.biz.soa.product.service.interfaces.impl.bbc;

import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.soa.product.service.interfaces.ProductValidator;
import org.codelogger.utils.ValueUtils;

/**
 * B类商品列表商品验证器
 *
 * @author david-liu
 * @date 2017年01月17日
 * @reviewer
 * @see
 */
public class TypeBProductListValidator implements ProductValidator {
    private static final long serialVersionUID = 5501322227179636938L;

    volatile private static ProductValidator instance = null;

    private TypeBProductListValidator() {

    }

    public static ProductValidator getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (TypeBProductListValidator.class) {
                if (instance == null) {
                    instance = new TypeBProductListValidator();
                }
                return instance;
            }
        }
    }

    @Override
    public Boolean doValidate(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) {

        Integer saleStatus = productPrototype.getSaleStatus();
        TypeBProductStockVo stockVo = productPrototype.getTypeBStockVo();
        if (saleStatus == null || stockVo == null) {
            return Boolean.FALSE;
        }

        // 商品是否在售
        Boolean onSale = saleStatus == SaleStatusEnum.ON_SALE.getValue();
        // 是否有库存
        Boolean hasStock;
        // 价格是否合法
        Boolean priceValid;
        if (!productPrototype.getOpenKuaiheMode()) {
            priceValid = ValueUtils.getValue(productPrototype.getWarehouseDepotMarketPrice()) > 0 &&
                    ValueUtils.getValue(productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel)) > 0;
        } else {
            priceValid = ValueUtils.getValue(productPrototype.getMarketPrice()) > 0 &&
                    ValueUtils.getValue(productPrototype.getFinalPrice(userLevel)) > 0 &&
                    ValueUtils.getValue(productPrototype.getWarehouseDepotMarketPrice()) > 0 &&
                    ValueUtils.getValue(productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel)) > 0;
        }


        return onSale && priceValid;
    }

    @Override
    public void doValidateWithException(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) throws DepotNextDoorException {
        if (!this.doValidate(productPrototype, userLevel, validateStock)) {
            throw new DepotNextDoorException("商品校验失败");
        }
    }
}
