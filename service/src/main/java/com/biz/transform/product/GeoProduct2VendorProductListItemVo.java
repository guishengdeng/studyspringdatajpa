package com.biz.transform.product;

import com.biz.core.util.MoneyConverter;
import com.biz.gbck.dao.mysql.po.product.GeoProduct;
import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.dao.mysql.po.product.ProductAudit;
import com.biz.gbck.vo.product.backend.VendorProductListItemVo;
import com.google.common.base.Function;

/**
 * 转换器(Product --> VendorProductListItemVo)
 * (未设置价格和库存信息)
 *
 * @author david-liu
 * @date 2016年12月23日
 * @reviewer
 * @see
 */
public class GeoProduct2VendorProductListItemVo implements Function<GeoProduct, VendorProductListItemVo> {

    @Override
    public VendorProductListItemVo apply(GeoProduct geoProduct) {
        VendorProductListItemVo itemVo = new VendorProductListItemVo();
        Product product = geoProduct.getProduct();
        ProductAudit audit = geoProduct.getProductAudit();
        itemVo.setProductCode(product.getProductCode());
        itemVo.setProductName(product.getName());
        itemVo.setLastUpdateTime(product.getUpdateTimestamp());
        itemVo.setInAudit(product.getInAudit());
        // 设置价格信息
        if (geoProduct.getMarketPrice() != null && geoProduct.getSalePrice() != null) {
            itemVo.setMarketPrice(MoneyConverter.instance.fen2yuan(geoProduct.getMarketPrice()));
            itemVo.setFinalPrice(MoneyConverter.instance.fen2yuan(geoProduct.getSalePrice()));
        }
        itemVo.setLocked(product.getLocked());
        itemVo.setAuditStatus(audit.getAuditStatus().getDescription());
        itemVo.setSaleStatus(geoProduct.getSaleStatus().getDescription());
        itemVo.setLogo(geoProduct.getProduct().getLogo());
        itemVo.setId(String.valueOf(geoProduct.getProduct().getId()));
        return itemVo;
    }

}
