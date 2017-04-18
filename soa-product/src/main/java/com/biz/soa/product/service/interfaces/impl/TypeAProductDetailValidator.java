package com.biz.soa.product.service.interfaces.impl;

import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.ProductPriceException;
import com.biz.gbck.exceptions.product.ProductSaleStatusException;
import com.biz.gbck.exceptions.product.ProductStockException;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.soa.product.service.interfaces.ProductValidator;

/**
 * A类商品商品详情校验器
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public class TypeAProductDetailValidator implements ProductValidator {
    private static final long serialVersionUID = -3849622087649326015L;

    private static TypeAProductDetailValidator instance = null;

    private TypeAProductDetailValidator() {
    }

    @Override
    public Boolean doValidate(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) {
        Boolean isOnSale = productPrototype.getSaleStatus() == SaleStatusEnum.ON_SALE.getValue();
        Boolean isStockValid = !validateStock || productPrototype.getCountryStock() != null && productPrototype.getCountryStock() > 0;
        Boolean isPriceValid = productPrototype.getFinalPrice(userLevel) != null && productPrototype.getFinalPrice(userLevel) > 0
                && productPrototype.getMarketPrice() != null && productPrototype.getMarketPrice() > 0;
        return isOnSale && isStockValid && isPriceValid;
    }

    @Override
    public void doValidateWithException(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) throws DepotNextDoorException {
        Boolean isOnSale = productPrototype.getSaleStatus() == SaleStatusEnum.ON_SALE.getValue();
        Boolean isStockValid = !validateStock || productPrototype.getCountryStock() != null && productPrototype.getCountryStock() > 0;
        Boolean isPriceValid = productPrototype.getFinalPrice(userLevel) != null && productPrototype.getFinalPrice(userLevel) > 0
                && productPrototype.getMarketPrice() != null && productPrototype.getMarketPrice() > 0;
        if (!isOnSale) {
            throw new ProductSaleStatusException("商品已经下架");
        }

        if (!isStockValid) {
            throw new ProductStockException("商品库存不足");
        }

        if (!isPriceValid) {
            throw new ProductPriceException("商品价格异常");
        }
    }

    public static ProductValidator getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (TypeAProductDetailValidator.class) {
                if (instance == null) {
                    instance = new TypeAProductDetailValidator();
                }
                return instance;
            }
        }
    }
}
