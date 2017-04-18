package com.biz.soa.product.service.interfaces.impl;

import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.ProductPriceException;
import com.biz.gbck.exceptions.product.ProductSaleStatusException;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.soa.product.service.interfaces.ProductValidator;
import org.codelogger.utils.ValueUtils;

/**
 * B类商品秒杀商品详情
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class TypeBSeckillProductDetailValidator implements ProductValidator {
    private static final long serialVersionUID = -6997105585497962149L;

    private static ProductValidator instance = null;

    private TypeBSeckillProductDetailValidator() {

    }

    @Override
    public Boolean doValidate(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) {
        boolean isOnSale = productPrototype.getSaleStatus() == SaleStatusEnum.ON_SALE.getValue();
        boolean isMarketPriceValid = ValueUtils.getValue(productPrototype.getWarehouseDepotMarketPrice()) >= 0;
        return isOnSale && isMarketPriceValid;
    }

    @Override
    public void doValidateWithException(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) throws DepotNextDoorException {
        boolean isOnSale = productPrototype.getSaleStatus() == SaleStatusEnum.ON_SALE.getValue();
        boolean isMarketPriceValid = ValueUtils.getValue(productPrototype.getWarehouseDepotMarketPrice()) > 0;
        if (!isOnSale) {
            throw new ProductSaleStatusException("当前商品已经下架");
        }

        if (!isMarketPriceValid) {
            throw new ProductPriceException("当前秒杀商品在当前地区不销售!");
        }
    }

    public static ProductValidator getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (TypeBSeckillProductDetailValidator.class) {
                if (instance == null) {
                    instance = new TypeBSeckillProductDetailValidator();
                }
                return instance;
            }
        }
    }
}
