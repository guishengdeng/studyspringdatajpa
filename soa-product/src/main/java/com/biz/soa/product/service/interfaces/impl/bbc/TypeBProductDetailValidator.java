package com.biz.soa.product.service.interfaces.impl.bbc;

import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.ProductPriceException;
import com.biz.gbck.exceptions.product.ProductSaleStatusException;
import com.biz.gbck.exceptions.product.ProductStockException;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.biz.soa.product.service.interfaces.ProductValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * B类商品商品详情验证器
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public class TypeBProductDetailValidator implements ProductValidator {
    private static final long serialVersionUID = -670579316191570064L;
    private static TypeBProductDetailValidator instance = null;

    public static ProductValidator getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (TypeBProductDetailValidator.class) {
                if (instance == null) {
                    instance = new TypeBProductDetailValidator();
                }
                return instance;
            }
        }
    }

    @Override
    public Boolean doValidate(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) {
        Boolean isOnSale = productPrototype.getSaleStatus() == SaleStatusEnum.ON_SALE.getValue();
        TypeBProductStockVo stockVo = productPrototype.getTypeBStockVo();
        Boolean isStockValid;
        isStockValid = !validateStock || stockVo != null && (stockVo.getDepotStock() > 0 || stockVo.getProvinceStock() > 0);
        Boolean isPriceValid;
        if (StringUtils.isNotBlank(productPrototype.getDepotCode())) {
            isPriceValid = productPrototype.getMarketPrice() != null && productPrototype.getMarketPrice() > 0
                    && productPrototype.getFinalPrice(userLevel) != null && productPrototype.getFinalPrice(userLevel) > 0
                    && productPrototype.getWarehouseDepotMarketPrice() != null
                    && productPrototype.getWarehouseDepotMarketPrice() > 0
                    && productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel) != null
                    && productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel) > 0;
        } else {
            isPriceValid = productPrototype.getWarehouseDepotMarketPrice() != null
                    && productPrototype.getWarehouseDepotMarketPrice() > 0
                    && productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel) != null
                    && productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel) > 0;
        }
        return isOnSale && isStockValid && isPriceValid;
    }

    @Override
    public void doValidateWithException(ProductPrototype productPrototype, Integer userLevel, Boolean validateStock) throws DepotNextDoorException {
        Boolean isOnSale = productPrototype.getSaleStatus() == SaleStatusEnum.ON_SALE.getValue();
        TypeBProductStockVo stockVo = productPrototype.getTypeBStockVo();
        Boolean isStockValid;
        isStockValid = !validateStock || stockVo != null && (stockVo.getDepotStock() > 0 || stockVo.getProvinceStock() > 0);
        Boolean isPriceValid;
        if (StringUtils.isNotBlank(productPrototype.getDepotCode())) {
            isPriceValid = productPrototype.getMarketPrice() != null && productPrototype.getMarketPrice() > 0
                    && productPrototype.getFinalPrice(userLevel) != null && productPrototype.getFinalPrice(userLevel) > 0
                    && productPrototype.getWarehouseDepotMarketPrice() != null
                    && productPrototype.getWarehouseDepotMarketPrice() > 0
                    && productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel) != null
                    && productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel) > 0;
        } else {
            isPriceValid = productPrototype.getWarehouseDepotMarketPrice() != null
                    && productPrototype.getWarehouseDepotMarketPrice() > 0
                    && productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel) != null
                    && productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel) > 0;
        }


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
}
