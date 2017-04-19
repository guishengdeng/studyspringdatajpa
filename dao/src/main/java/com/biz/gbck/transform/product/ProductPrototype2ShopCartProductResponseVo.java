package com.biz.gbck.transform.product;

import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.product.frontend.ShopCartProductResponseVo;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.google.common.base.Function;

/**
 * 转换器(ProductPrototype --> ShopCartProductResponseVo)
 *
 * @author david-liu
 * @date 2017年01月18日
 * @reviewer
 * @see
 */
public class ProductPrototype2ShopCartProductResponseVo implements Function<ProductPrototype, ShopCartProductResponseVo> {

    private Integer userLevel;

    public ProductPrototype2ShopCartProductResponseVo(Integer userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public ShopCartProductResponseVo apply(ProductPrototype productPrototype) {
        ShopCartProductResponseVo vo = new ShopCartProductResponseVo();
        vo.setProductId(productPrototype.getProductId());
        vo.setProductName(productPrototype.getProductName());
        vo.setProductCode(productPrototype.getProductCode());
        VendorTypeEnum vendorType;
        if (productPrototype.getProductType() == VendorTypeEnum.TYPE_A.getValue()) {
            vendorType = VendorTypeEnum.TYPE_A;
        } else {
            vendorType = VendorTypeEnum.TYPE_B;
        }
        vo.setVendorType(vendorType);
        vo.setSubTitle(productPrototype.getSubTitle());
        vo.setLogo(productPrototype.getLogo());
        vo.setVendorId(String.valueOf(productPrototype.getVendorId()));
        vo.setMarketPrice(productPrototype.getMarketPrice());
        vo.setFinalPrice(productPrototype.getFinalPrice(userLevel));
        vo.setWarehouseDepotMarketPrice(productPrototype.getWarehouseDepotMarketPrice());
        vo.setWarehouseDepotFinalPrice(productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel));
        TypeBProductStockVo stockVo = productPrototype.getTypeBStockVo();
        if (productPrototype.getProductType() == VendorTypeEnum.TYPE_B.getValue()) {
            vo.setQuantity(stockVo.getDepotStock());
            vo.setWarehouseQuantity(stockVo.getProvinceStock());
        } else {
            vo.setQuantity(productPrototype.getCountryStock());
        }
        vo.setStatus(productPrototype.getShowStatus());
        return vo;
    }
}
