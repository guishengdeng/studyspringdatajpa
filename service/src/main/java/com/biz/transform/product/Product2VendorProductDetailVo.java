package com.biz.transform.product;

import com.biz.gbck.dao.mysql.po.product.ExtendProperty;
import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.vo.product.backend.ExtendVo;
import com.biz.gbck.vo.product.backend.VendorProductDetailVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * 转换器(商品 --> 商户商品详情(已审核通过) Vo)
 * 未设置商家名称, 商品扩展属性
 *
 * @author david-liu
 * @date 2016年12月26日
 * @reviewer
 * @see
 */
public class Product2VendorProductDetailVo implements Function<Product, VendorProductDetailVo> {

    @Override
    public VendorProductDetailVo apply(Product po) {
        VendorProductDetailVo vo = new VendorProductDetailVo();
        vo.setId(String.valueOf(po.getId()));
        vo.setProductCode(po.getProductCode());
        vo.setName(po.getName());
        vo.setVendorId(String.valueOf(po.getVendorId()));
        vo.setSubTitle(po.getSubTitle());
        vo.setI18nCode(po.getI18nCode());
        vo.setBreif(po.getBreif());
        if (po.getBrand() != null) {
            vo.setBrandId(String.valueOf(po.getBrand().getId()));
        }
        if (po.getCategory() != null) {
            vo.setCategoryId(String.valueOf(po.getCategory().getId()));
        }
        vo.setIntroImages(po.getIntroImages());
        vo.setLogo(po.getLogo());
        vo.setImages(po.getImages());
        vo.setInAudit(po.getInAudit());
        vo.setSeoTitle(po.getSeoTitle());
        vo.setSeoKeywords(po.getSeoKeywords());
        vo.setSeoDescription(po.getSeoDescription());
        List<ExtendProperty> properties = po.getProperties();
        List<ExtendVo> extendVos = Lists.newArrayList();
        for (ExtendProperty property : properties) {
            ExtendVo extendVo = new ExtendVo();
            if (property.getProductExtend().getId() != null) {
                extendVo.setExtendId(String.valueOf(property.getProductExtend().getId()));
            }
            if (property.getId() != null) {
                extendVo.setPropertyId(String.valueOf(property.getId()));
            }
            extendVos.add(extendVo);
        }
        vo.setProperties(extendVos);
        return vo;
    }
}
