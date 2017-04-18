package com.biz.transform.product;

import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.product.frontend.ProductApartTagVo;
import com.biz.gbck.vo.product.frontend.ProductListItemVo;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.product.frontend.interfaces.ProductPrototype;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import java.util.List;
import org.codelogger.utils.ValueUtils;

/**
 * 转换器(ProductPrototype -> ProductListItemVo)
 *
 * @author david-liu
 * @date 2017年01月13日
 * @reviewer
 */
public class ProductPrototype2ProductListItemVo implements Function<ProductPrototype, ProductListItemVo> {

    private Integer userLevel;

    private String traceId;

    public ProductPrototype2ProductListItemVo(Integer userLevel, String traceId) {
        this.userLevel = userLevel;
        this.traceId = traceId;
    }

    @Override
    public ProductListItemVo apply(ProductPrototype productPrototype) {
        ProductListItemVo itemVo = new ProductListItemVo();
        itemVo.setProductCode(productPrototype.getProductCode());
        itemVo.setProductId(productPrototype.getProductId());
        itemVo.setProductName(productPrototype.getProductName());
        itemVo.setLogo(productPrototype.getLogo());
        itemVo.setProductType(productPrototype.getProductType());
        itemVo.setVendorId(String.valueOf(productPrototype.getVendorId()));
        itemVo.setDepotCode(String.valueOf(productPrototype.getDepotCode()));
        if (productPrototype.getProductType() == VendorTypeEnum.TYPE_A.getValue()) {
            itemVo.setVendorProductCode(productPrototype.getVendorProductCode());
            itemVo.setMarketPrice(productPrototype.getMarketPrice());
            itemVo.setFinalPrice(productPrototype.getFinalPrice(userLevel));
        } else {
            TypeBProductStockVo stockVo = productPrototype.getTypeBStockVo();
            if (ValueUtils.getValue(stockVo.getDepotStock()) > 0) {
                itemVo.setMarketPrice(productPrototype.getMarketPrice());
                itemVo.setFinalPrice(productPrototype.getFinalPrice(userLevel));
            } else {
                itemVo.setMarketPrice(productPrototype.getWarehouseDepotMarketPrice());
                itemVo.setFinalPrice(productPrototype.getWarehouseDepotFinalPrice(productPrototype.getDepotCode(), userLevel));
            }
        }
        List<String> apartTagStrings = productPrototype.getApartTags();
        List<ProductApartTagVo> tagVos = Lists.newArrayList();
        for (String apartTagString : apartTagStrings) {
            ProductApartTagVo vo = new ProductApartTagVo();
            vo.setTagName(apartTagString);
            tagVos.add(vo);
        }
        itemVo.setApartTags(tagVos);
        itemVo.setPredictArrival(productPrototype.getPredictArrival());
        itemVo.setDeliverType(productPrototype.getDeliverType());
        itemVo.setTraceId(this.traceId);
        return itemVo;
    }
}
