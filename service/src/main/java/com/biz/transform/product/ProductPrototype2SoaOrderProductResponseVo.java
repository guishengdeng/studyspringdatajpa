package com.biz.transform.product;

import com.biz.gbck.enums.product.ProductShowStatus;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.product.frontend.SoaOrderProductResponseVo;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.google.common.base.Function;

/**
 * 转换器(ProductPrototype --> SoaOrderProductResponseVo)
 *
 * @author david-liu
 * @date 2017年01月24日
 * @reviewer
 */
public class ProductPrototype2SoaOrderProductResponseVo implements Function<ProductPrototype, SoaOrderProductResponseVo> {

    private Integer userLevel;

    public ProductPrototype2SoaOrderProductResponseVo(Integer userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public SoaOrderProductResponseVo apply(ProductPrototype productPrototype) {
        SoaOrderProductResponseVo vo = new SoaOrderProductResponseVo();
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
        vo.setWeight(productPrototype.getWeight());
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
        vo.setStatus(productPrototype.getSaleStatus() == SaleStatusEnum.ON_SALE.getValue() ? ProductShowStatus.OFF_SALE : ProductShowStatus.NORMAL);
        vo.setBrandId(productPrototype.getBrandId());
        vo.setCategoryId(productPrototype.getCategoryId());
        return vo;
    }
}
