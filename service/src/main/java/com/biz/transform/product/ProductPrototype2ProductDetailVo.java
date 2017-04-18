package com.biz.transform.product;

import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.product.backend.ProductDetailVo;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.google.common.base.Function;

/**
 * 转换器(ProductPrototype --> ProductDetailVo)
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public class ProductPrototype2ProductDetailVo implements Function<ProductPrototype, ProductDetailVo> {

    private Integer userLevel;

    public ProductPrototype2ProductDetailVo(Integer userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public ProductDetailVo apply(ProductPrototype p) {
        ProductDetailVo vo = new ProductDetailVo();
        vo.setProductId(String.valueOf(p.getProductId()));
        vo.setProductCode(p.getProductCode());
        vo.setProductName(p.getProductName());
        vo.setSubTitle(p.getSubTitle());
        vo.setLogo(p.getLogo());
        vo.setVendorId(String.valueOf(p.getVendorId()));
        vo.setProductType(p.getProductType());
        vo.setProductImages(p.getProductImages());
        vo.setWeight(p.getWeight());
        vo.setMarketPrice(p.getMarketPrice());
        vo.setFinalPrice(p.getFinalPrice(userLevel));
        vo.setBrandName(p.getBrandName());
        vo.setCategoryName(p.getCategoryName());
        vo.setIntroImages(p.getIntroImages());
        vo.setPredictArrival(p.getPredictArrival());
        if (p.getProductType() == VendorTypeEnum.TYPE_A.getValue()) {
            vo.setCountryStock(p.getCountryStock());
        } else {
            TypeBProductStockVo stockVo = p.getTypeBStockVo();
            vo.setDepotStock(stockVo.getDepotStock());
            vo.setProvinceStock(stockVo.getProvinceStock());
            vo.setWarehouseDepotMarketPrice(p.getWarehouseDepotMarketPrice());
            vo.setWarehouseDepotFinalPrice(p.getWarehouseDepotFinalPrice(p.getDepotCode(), userLevel));
        }

        return vo;
    }
}
