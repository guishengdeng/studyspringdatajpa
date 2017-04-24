package com.biz.gbck.transform.product;

import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.product.frontend.UserProductItemVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.google.common.base.Function;
import org.codelogger.utils.StringUtils;

/**
 * 转换器(ProductPrototype -> DateUserFootPrintProductItemVo)
 *
 * @author david-liu
 * @date 2017年01月16日
 * @reviewer
 */
public class ProductPrototype2UserProductItemVo implements Function<ProductPrototype, UserProductItemVo> {

    private Integer userLevel;

    public ProductPrototype2UserProductItemVo(Integer userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public UserProductItemVo apply(ProductPrototype productPrototype) {
        UserProductItemVo itemVo = new UserProductItemVo();
        itemVo.setProductId(String.valueOf(productPrototype.getProductId()));
        itemVo.setProductCode(productPrototype.getProductCode());
        itemVo.setProductName(productPrototype.getProductName());
        itemVo.setProductType(productPrototype.getProductType());
        itemVo.setVendorId(String.valueOf(productPrototype.getVendorId()));
        itemVo.setLogo(productPrototype.getLogo());
        itemVo.setSaleStatus(productPrototype.getSaleStatus());
        if (productPrototype.getProductType() == VendorTypeEnum.TYPE_A.getValue()) {
            itemVo.setMarketPrice(productPrototype.getMarketPrice());
            itemVo.setFinalPrice(productPrototype.getFinalPrice(userLevel));
        } else {
            if (StringUtils.isBlank(productPrototype.getDepotCode())) {
                itemVo.setMarketPrice(productPrototype.getWarehouseDepotMarketPrice());
                itemVo.setFinalPrice(productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel));
            } else {
                itemVo.setMarketPrice(productPrototype.getMarketPrice());
                itemVo.setFinalPrice(productPrototype.getFinalPrice(userLevel));
            }
        }
        itemVo.setSubTitle(productPrototype.getSubTitle());
        Integer stock;
        if (productPrototype.getProductType() == VendorTypeEnum.TYPE_A.getValue()) {
            stock = productPrototype.getCountryStock();
        } else {
            TypeBProductStockVo stockVo = productPrototype.getTypeBStockVo();
            Integer depotStock = stockVo.getDepotStock() == null ? 0 : stockVo.getDepotStock();
            Integer provinceStock = stockVo.getProvinceStock() == null ? 0 : stockVo.getProvinceStock();
            stock = depotStock + provinceStock;
        }
        itemVo.setStock(stock);
        return itemVo;
    }
}
