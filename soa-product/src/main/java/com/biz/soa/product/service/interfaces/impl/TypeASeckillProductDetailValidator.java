package com.biz.soa.product.service.interfaces.impl;

import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.ProductSaleStatusException;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.soa.product.service.interfaces.ProductValidator;

/**
 * A类商品秒杀商品详情验证器
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class TypeASeckillProductDetailValidator implements ProductValidator {
    private static final long serialVersionUID = -2102043258514830951L;

    private static ProductValidator instance = null;

    private TypeASeckillProductDetailValidator() {

    }

    public static ProductValidator getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (TypeASeckillProductDetailValidator.class) {
                if (instance == null) {
                    instance = new TypeASeckillProductDetailValidator();
                }
                return instance;
            }
        }
    }

    @Override
    public Boolean doValidate(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) {
        return productPrototype.getSaleStatus() == SaleStatusEnum.ON_SALE.getValue();
    }

    @Override
    public void doValidateWithException(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) throws DepotNextDoorException {
        Boolean isOnSale = productPrototype.getSaleStatus() == SaleStatusEnum.ON_SALE.getValue();
        if (!isOnSale) {
            throw new ProductSaleStatusException("当前商品已经下架");
        }
    }
}
